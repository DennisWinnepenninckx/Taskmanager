package com.dennis.taskmanager.Service;

import com.dennis.taskmanager.DTO.SubTaskDTO;
import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.model.SubTask;
import com.dennis.taskmanager.model.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public interface Service {
    List<TaskDTO> getAll();

    void addTask(TaskDTO task);

    void removeTask(UUID uuid);

    TaskDTO get(UUID uuid);

    void update(TaskDTO dummy);

    List<SubTaskDTO> getAllSubTask(UUID uuid);

    public void addSubTask(SubTaskDTO subtask);
}
