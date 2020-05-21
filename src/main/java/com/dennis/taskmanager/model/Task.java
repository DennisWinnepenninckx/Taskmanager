package com.dennis.taskmanager.model;

import com.dennis.taskmanager.db.LocalDatabase;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Task {
    @Id
    private UUID uuid;
    @NotEmpty(message = "Title may not be empty.")
    private String title;
    @NotEmpty(message = "Description may not be empty.")
    private String description;
    private String dateString;
    @NotNull(message = "Datum mag niet leeg zijn tho")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future(message = "Task must be in the futureeeee.")
    private LocalDateTime date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SubTask> subTasks;


    public Task(String title, LocalDateTime date, String description) {
        uuid = UUID.randomUUID();
        this.title = title;
        this.date = date;
        this.description = description;
        this.dateString = getDateString();
        this.subTasks = new ArrayList<>();

    }
    public Task(UUID uuid, String title, LocalDateTime date, String description) {
        this.uuid = uuid;
        this.title = title;
        this.date = date;
        this.description = description;
        this.dateString = getDateString();
        this.subTasks = new ArrayList<>();

    }
    public Task(){
        uuid = UUID.randomUUID();
        this.subTasks = new ArrayList<>();
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public String getDateString() {
        return date.getDayOfMonth() + "/" + getlowerMonth() + "/" + date.getYear() + " " + formatHour();
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return title + ": due " + getlowerMonth() + " " + date.getDayOfMonth() +
                " " + date.getYear() + " at " + formatHour();
    }
    private String getlowerMonth(){
        String dateString = date.getMonth()+ "";
        dateString = dateString.toLowerCase();
        return dateString.substring(0, 1).toUpperCase() + dateString.substring(1);
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    private String formatHour(){
        int times = this.date.getHour();
        int timedummy=0;
        String res = "";
        if (times > 12){
            timedummy = date.getHour() -12;
            res += timedummy + " pm";
        }else{
            timedummy = date.getHour();
            res += timedummy + " am";
        }
        return res;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(date, task.date)  &&  Objects.equals(uuid, task.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date);
    }
    public void changeEverything(Task task){
        this.setDescription(task.getDescription());
        this.setTitle(task.getTitle());
        this.setDate(task.getDate());
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
    public void addSubtask(SubTask subTask){
        subTasks.add(subTask);
    }
    public void removeSubTask(SubTask subTask){
        Iterator iterator = subTasks.iterator();
        while (iterator.hasNext()){
            SubTask s = (SubTask) iterator.next();
            if (s.equals(subTask)){
                iterator.remove();
            }
        }
    }
}
