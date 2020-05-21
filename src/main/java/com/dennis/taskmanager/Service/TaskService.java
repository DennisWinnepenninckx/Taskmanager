package com.dennis.taskmanager.Service;

import com.dennis.taskmanager.DTO.SubTaskDTO;
import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.model.SubTask;
import com.dennis.taskmanager.model.Task;
import com.dennis.taskmanager.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class TaskService implements Service {
    @Autowired
    TaskRepo taskRepo;

    @Override
    public List<TaskDTO> getAll() {

        return taskRepo.findAll().stream().map(h -> {
            return makeTaskDTO(h);
        }).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        try {
            Task task = new Task();
            task.setUuid(taskDTO.getUuid());
            task.setDate(taskDTO.getDate());
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setDateString(taskDTO.getDateString());
            taskRepo.save(task);
        }catch (Exception e){
            throw new TransactionSystemException(e.getMessage());
        }
    }

    @Override
    public void removeTask(UUID uuid) {
        taskRepo.deleteById(uuid);
    }

    @Override
    public TaskDTO get(UUID uuid) {
        Task t;
        try {
            t = taskRepo.findById(uuid).orElse(null);
            if (t == null) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Task not found");
        }
        return makeTaskDTO(t);
    }
    @Override
    public void addSubTask(SubTaskDTO subtaskDTO) {
        SubTask subTask = new SubTask(subtaskDTO.getParent(), subtaskDTO.getTitle(), subtaskDTO.getDescription());
        Task t = taskRepo.findById(subTask.getParent()).orElse(null);
        t.addSubtask(subTask);
        taskRepo.save(t);
    }

    public void update(TaskDTO dummy) {
        Task task = makeTask(dummy);
        taskRepo.save(task);
    }

    @Override
    public List<SubTaskDTO> getAllSubTask(UUID uuid) {
        Task t = taskRepo.findById(uuid).orElse(null);
        return t.getSubTasks()==null?new ArrayList<SubTaskDTO>():t.getSubTasks().stream().map( h -> {return makeSubtaskDTO(h);}).collect(Collectors.toList());
    }

    public TaskDTO makeTaskDTO(Task task){
        return new TaskDTO(task.getUuid(),task.getTitle(),task.getDate(),task.getDescription());
    }
    //    public SubTaskDTO(UUID id, UUID parent, String title, String description) {
    public SubTaskDTO makeSubtaskDTO(SubTask task){
        return new SubTaskDTO(task.getUuid(),task.getParent(),task.getTitle(),task.getDescription());
    }
    public Task makeTask(TaskDTO task){
        return new Task(task.getUuid(),task.getTitle(),task.getDate(),task.getDescription());
    }
}
