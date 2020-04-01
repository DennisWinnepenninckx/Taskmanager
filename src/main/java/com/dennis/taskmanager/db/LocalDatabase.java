package com.dennis.taskmanager.db;

import com.dennis.taskmanager.model.SubTask;
import com.dennis.taskmanager.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class LocalDatabase {
    HashMap<UUID,Task> tasks;
    public LocalDatabase(){
        tasks = new HashMap<>();
        Task t = new Task("Make the CSS look decent", LocalDateTime.of(2020,5,5,5,5),"Just make it so we won't puke.");
        Task t2 = new Task("Fix proefstage pls", LocalDateTime.of(2020,7,5,5,5),"Contact IT comopanies to fix proefstage.");
        addTask(t);
        addTask(t2);
    }
    public void addTask(Task task){
        tasks.put(task.getUuid(),task);
    }
    public void removeTask(Task task){
        tasks.remove(task);
    }
    public void removeTask(UUID uuid){
        tasks.remove(uuid);
    }
    public Task getTask(UUID uuid){
       return tasks.get(uuid);
    }
    public Map<UUID,Task> getAll(){
        return tasks;
    }

    public void editTask(UUID id, Task task){
        tasks.get(id).changeEverything(task);
    }
}
