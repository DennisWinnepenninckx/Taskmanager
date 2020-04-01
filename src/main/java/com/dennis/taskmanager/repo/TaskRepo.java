package com.dennis.taskmanager.repo;

import com.dennis.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {

}
