package com.dennis.taskmanager;

import com.dennis.taskmanager.DTO.SubTaskDTO;
import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.Service.TaskService;
import com.dennis.taskmanager.model.SubTask;
import com.dennis.taskmanager.model.Task;
import com.dennis.taskmanager.repo.TaskRepo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;
@Transactional
@SpringBootTest
public class TaskmanagerTaskServiceTests {
    @Autowired
    private TaskService taskService;
    private LocalDateTime future = LocalDateTime.of(2030,5,1,1,1,1);
    private TaskDTO taskDTO = new TaskDTO("Title",future , "Description");

    @Test
    public void testUpdateTask(){
        taskService.addTask(taskDTO);
        TaskDTO taskNew = new TaskDTO(taskDTO.getUuid(),"UpdatedTitle",future , "Description");

        taskService.update(taskNew);
        assertEquals(taskService.get(taskDTO.getUuid()).getTitle(), taskNew.getTitle());
    }
    @Test
    public void testGetAllSubTasks(){
        SubTaskDTO subTaskDTO = new SubTaskDTO(taskDTO.getUuid(), "Title","Description");
        SubTaskDTO subTaskDTO2 = new SubTaskDTO(taskDTO.getUuid(), "Title","Description");

        taskService.addTask(taskDTO);
        taskService.addSubTask(subTaskDTO);
        taskService.addSubTask(subTaskDTO2);

        List<SubTaskDTO> subTasks = taskService.getAllSubTask(taskDTO.getUuid());

        assertNotNull(subTasks);
        assertFalse(subTasks.isEmpty());
        assertEquals(2, subTasks.size());


    }
    @Test
    public void testAddSubTask(){
        SubTaskDTO subTaskDTO = new SubTaskDTO(taskDTO.getUuid(), "Title","Description");
        taskService.addTask(taskDTO);
        assertEquals(taskService.getAllSubTask(taskDTO.getUuid()).size(),0);
        taskService.addSubTask(subTaskDTO);
        assertEquals(taskService.getAllSubTask(taskDTO.getUuid()).size(),1);
    }
    @Test
    public void testGetTask(){
        taskService.addTask(taskDTO);


        assertEquals(taskDTO.getUuid(), taskService.get(taskDTO.getUuid()).getUuid());

    }
    @Test
    public void testGetTasks(){
        int size =  taskService.getAll().size();
        taskService.addTask(taskDTO);

        List<TaskDTO> tasks = taskService.getAll();
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(size+1, tasks.size());
        TaskDTO task = taskService.get(taskDTO.getUuid());
        assertNotNull(task);

    }

    @Test
    public void testRemoveTask(){
        int size =  taskService.getAll().size();

        TaskDTO taskDTO2 = new TaskDTO("Title", future, "Description");
        taskService.addTask(taskDTO);
        assertEquals(size+1, taskService.getAll().size());
        taskService.addTask(taskDTO2);
        assertEquals(size+2, taskService.getAll().size());
        taskService.removeTask(taskDTO.getUuid());
        assertEquals(size+1, taskService.getAll().size());
    }

    @Test
    public void testTaskToTaskDTO(){
        Task task = new Task("Title",future, "Description");
        TaskDTO taskDTO = taskService.makeTaskDTO(task);

        assertEquals(task.getUuid(), taskDTO.getUuid());
        assertEquals(task.getTitle(), taskDTO.getTitle());
        assertEquals(task.getDate(), taskDTO.getDate());
        assertEquals(task.getDescription(), taskDTO.getDescription());
    }
}
