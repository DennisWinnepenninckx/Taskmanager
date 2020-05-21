package com.dennis.taskmanager;
import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.Service.TaskService;
import com.dennis.taskmanager.db.LocalDatabase;
import com.dennis.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
public class TaskManagerDBTests {
    LocalDatabase db = new LocalDatabase();
    Task dummy = new Task("Test task", LocalDateTime.of(2020,5,5,5,5),"Test Desc");

    @Test
    public void testLocalDBStartsWith2Tasks(){
        assertNotNull(db.getAll());
        assertEquals(db.getAll().size(),2);
    }
    @Test
    public void testLocalDBClearAllTasks(){
        db.removeAll();
        assertEquals(db.getAll().size(),0);
    }
    @Test
    public void testLocalDBAddTask(){
        int size = db.getAll().size();
        db.addTask(dummy);
        assertEquals(db.getAll().size(), size+1);
        assertNotNull(db.getAll().get(dummy.getUuid()));
    }
    @Test
    public void testLocalDBGetTask(){
        db.addTask(dummy);
        Task ACopyOfDummy = db.getTask(dummy.getUuid());
        assertNotNull(ACopyOfDummy);
        assertEquals(dummy, ACopyOfDummy);
    }
    @Test
    public void testLocalDBRemoveTask(){
        db.addTask(dummy);

        int tasks = db.getAll().size();
        db.removeTask(dummy.getUuid());
        assertEquals(db.getAll().size(), tasks-1);
    }
    @Test
    public void testLocalDBEditTask(){
        db.addTask(dummy);

        db.editTask(dummy.getUuid(), new Task("New title", LocalDateTime.of(2020,5,5,5,5), "New description"));

        assertNotNull(db.getTask(dummy.getUuid()).getTitle());
        assertEquals(db.getTask(dummy.getUuid()).getTitle(),"New title");
        assertEquals(db.getTask(dummy.getUuid()).getDescription(),"New description");
        assertEquals(db.getTask(dummy.getUuid()).getDate(),LocalDateTime.of(2020,5,5,5,5));
    }

}
