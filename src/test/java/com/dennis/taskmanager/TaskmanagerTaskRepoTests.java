package com.dennis.taskmanager;

import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.model.Task;
import com.dennis.taskmanager.repo.TaskRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskmanagerTaskRepoTests {
    @Autowired
    TaskRepo taskRepo;
    private LocalDateTime future = LocalDateTime.of(2030,5,1,1,1,1);
    private Task task = new Task("Title",future , "Description");

    @Test
    public void testSaveTaskToRepo(){
        taskRepo.save(task);
        assertNotNull(taskRepo.findAll());
    }
    @Test
    public void testRemoveTaskFromRepo(){
        taskRepo.save(task);
        assertNotNull(taskRepo.findAll());
        taskRepo.deleteById(task.getUuid());
    }
    @Test
    public void testFindAllTasks(){
        int saves = taskRepo.findAll().size();
        taskRepo.save(task);
        assertNotNull(taskRepo.findAll());
        assertEquals(taskRepo.findAll().size(),saves+1);
    }
    @Test
    public void testFindTaskById(){
        taskRepo.save(task);
        Task taskInRepo = taskRepo.findById(task.getUuid()).orElse(null);
        assertEquals(task.getUuid(), taskInRepo.getUuid());
    }
}
