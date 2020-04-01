package com.dennis.taskmanager;

import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.Service.TaskService;
import com.dennis.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TaskmanagerTaskTests {
    @Autowired
    private TaskService taskService;
    private LocalDateTime future = LocalDateTime.of(2030,5,1,1,1,1);
    private TaskDTO taskDTO = new TaskDTO("Title",future , "Description");

    @Test
    public void testPastTask(){
        TaskDTO pastTask = new TaskDTO("Title", LocalDateTime.of(2000,1,1,1,1,1), "Descrion");
        assertThrows(TransactionSystemException.class, () -> {taskService.addTask(pastTask);});
        List<TaskDTO> d = taskService.getAll();
    }
    @Test
    public void testAddNormalTask(){
        taskService.addTask(taskDTO);
        assertEquals(1, taskService.getAll().size());
    }
    @Test
    public void testEmptyTitleTask(){
        TaskDTO emptyTitle = new TaskDTO(null, future, "Description");
        assertThrows(TransactionSystemException.class, () -> {taskService.addTask(emptyTitle);});
    }
    @Test
    public void testEmptyDescriptionTask(){
        TaskDTO emptyDescription = new TaskDTO("Title", future, null);
        assertThrows(TransactionSystemException.class, () -> {taskService.addTask(emptyDescription);});
    }
    @Test
    public void testEmptyDateTask(){
        TaskDTO EmptyDate = new TaskDTO("Title", null, "Description");
        assertThrows(TransactionSystemException.class, () -> {taskService.addTask(EmptyDate);});
    }
}
