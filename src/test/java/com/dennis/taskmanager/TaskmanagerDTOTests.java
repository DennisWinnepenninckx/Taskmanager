package com.dennis.taskmanager;

import com.dennis.taskmanager.DTO.SubTaskDTO;
import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SpringBootTest
public class TaskmanagerDTOTests {
    //Added DTO tests for the 65%
    @Test
    public void testChangeSubTaskDTOParent(){
        Task dummy = new Task("Title", LocalDateTime.now(), "description");
        Task dummy2 = new Task("Title", LocalDateTime.now(), "description");
        SubTaskDTO subTaskDTO = new SubTaskDTO(dummy.getUuid());
        subTaskDTO.setParent(dummy2.getUuid());

        assertEquals(subTaskDTO.getParent(), dummy2.getUuid());
    }
    @Test
    public void testSubTaskDTOSetTitle(){
        Task dummy = new Task("Title", LocalDateTime.now(), "description");
        SubTaskDTO subTaskDTO = new SubTaskDTO(dummy.getUuid());

        subTaskDTO.setTitle("New title");
        assertEquals("New title", subTaskDTO.getTitle());
    }
    @Test
    public void testSubTaskDTOSetDescription(){
        Task dummy = new Task("Title", LocalDateTime.now(), "description");
        SubTaskDTO subTaskDTO = new SubTaskDTO(dummy.getUuid());

        subTaskDTO.setDescription("New description");
        assertEquals("New description", subTaskDTO.getDescription());
    }
    @Test
    public void testSubTaskDTOEquals(){
        Task dummy = new Task("Title", LocalDateTime.now(), "description");
        SubTaskDTO subTaskDTO = new SubTaskDTO(dummy.getUuid());
        SubTaskDTO AnotherSubTaskDTO = new SubTaskDTO(dummy.getUuid());
        SubTaskDTO copyOfsubTaskDTO = subTaskDTO;

        assertNotEquals(subTaskDTO, AnotherSubTaskDTO);
        assertEquals(subTaskDTO, copyOfsubTaskDTO);
    }
    @Test
    public void testTaskDTOEquals(){
        TaskDTO dummy = new TaskDTO("Title", LocalDateTime.now(), "description");
        TaskDTO dummyCopy =dummy;
        TaskDTO AnotherTask = new TaskDTO("Titles", LocalDateTime.now(), "description");

        assertNotEquals(dummy, AnotherTask);
        assertEquals(dummyCopy, dummy);
    }
    @Test
    public void testTaskDTOSetTitle(){
        TaskDTO dummy = new TaskDTO("Title", LocalDateTime.now(), "description");

        dummy.setTitle("New title");
        assertEquals("New title", dummy.getTitle());
    }
    @Test
    public void testTaskDTOSetDescription(){
        TaskDTO dummy = new TaskDTO("Title", LocalDateTime.now(), "description");

        dummy.setDescription("New description");
        assertEquals("New description", dummy.getDescription());
    }
    @Test
    public void testTaskDTOSetDate(){
        TaskDTO dummy = new TaskDTO("Title", LocalDateTime.now(), "description");

        dummy.setDate(LocalDateTime.of(2030,5,1,1,1,1));
        assertEquals(LocalDateTime.of(2030,5,1,1,1,1), dummy.getDate());
    }
}
