package com.dennis.taskmanager.DTO;

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


public class TaskDTO {
    @Id
    private UUID uuid;
    @NotEmpty(message = "Title may not be empty.")
    private String title;
    @NotEmpty(message = "Description may not be empty.")
    private String description;
    @NotNull(message = "Datum mag niet leeg zijn tho")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future(message = "Task must be in the futureeeee.")
    private LocalDateTime date;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public TaskDTO(String title, LocalDateTime date, String description) {
        uuid = UUID.randomUUID();
        this.title = title;
        this.date = date;
        this.description = description;
    }
    public TaskDTO(UUID uuid, String title, LocalDateTime date, String description) {
        this.uuid = uuid;
        this.title = title;
        this.date = date;
        this.description = description;
    }
    public TaskDTO(){
        uuid = UUID.randomUUID();
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
        TaskDTO task = (TaskDTO) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(date, task.date) &&  Objects.equals(uuid, task.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date);
    }


}
