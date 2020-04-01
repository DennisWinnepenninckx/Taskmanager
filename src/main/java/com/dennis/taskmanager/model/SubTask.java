package com.dennis.taskmanager.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.UUID;

@Entity
public class SubTask {
    @NotEmpty
    private String title, description;
    @Id
    private UUID uuid;
    private UUID parent;
    public SubTask(String title, String description) {
        this.title = title;
        this.description = description;
        uuid = UUID.randomUUID();
    }
    public SubTask(UUID parent, String title, String description) {
        this.parent = parent;
        this.title = title;
        this.description = description;
        uuid = UUID.randomUUID();
    }
    public SubTask(UUID parent){
        this.parent = parent;
        uuid = UUID.randomUUID();
    }

    public UUID getParent() {
        return parent;
    }

    public void setParent(UUID parent) {
        this.parent = parent;
    }

    public SubTask(){
        uuid = UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Subtask: " + title + ", " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubTask subTask = (SubTask) o;
        return Objects.equals(title, subTask.title) &&
                Objects.equals(description, subTask.description) &&
                Objects.equals(uuid, subTask.uuid) &&
                Objects.equals(parent, subTask.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, uuid, parent);
    }
}
