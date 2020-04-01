package com.dennis.taskmanager.Controller;

import com.dennis.taskmanager.DTO.SubTaskDTO;
import com.dennis.taskmanager.DTO.TaskDTO;
import com.dennis.taskmanager.Service.TaskService;
import com.dennis.taskmanager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String Home(Model model) {
        model.addAttribute("page", "Home");
        return "index";
    }

    @GetMapping("/tasks")
    public String overview(Model model) {
        model.addAttribute("Tasks", taskService.getAll());
        model.addAttribute("page", "Overview");
        return "overview";
    }

    @GetMapping("/tasks/{id}") //{id} indicates a variable will be used in the url.
    //Getmapping says 'If there is a get request with the url of "/tasks/<var>", call the getTask function'
    public String getTask(Model model, @PathVariable("id") String id) { //@Pathvariable indicates this variable will be used in the url
        try {
            model.addAttribute("subTaskDTO", new SubTaskDTO());
            model.addAttribute("Task", taskService.get(UUID.fromString(id)));
            model.addAttribute("subTasks", taskService.getAllSubTask(UUID.fromString(id)));
            model.addAttribute("page", taskService.get(UUID.fromString(id)).getTitle() + " ");

        } catch (Exception e) {
            return "redirect:/error";
        }
        return "task";
    }

    @GetMapping("tasks/new")
    public String newTask(Model model) {
        model.addAttribute("page", "Add Task");
        model.addAttribute("taskDTO", new TaskDTO());
        return "newTask";
    }

    @GetMapping("error")
    public String error(Model model) {
        model.addAttribute("page", "Error");
        return "error";
    }

    @PostMapping("/tasks/CreateTask")
    public String addTask(Model model, @ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", "Add Task");
            return "newTask";
        }
        taskService.addTask(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/editPage/{id}")
    public String editPage(Model model, @PathVariable("id") String id) {
        try {
            TaskDTO task = taskService.get(UUID.fromString(id));
            model.addAttribute("taskDTO", task);
            model.addAttribute("page", "Edit " + task.getTitle() + " ");
        } catch (Exception e) {
            return "redirect:/error";
        }
        return "edit";
    }

    @PostMapping("/tasks/editTask")
    public String editTask(Model model, @Valid TaskDTO task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //model.addAttribute("task",task);
            model.addAttribute("page", "Edit " + task.getTitle());
            return "edit";
        }
        taskService.update(task);
        return "redirect:/tasks";

    }

    @PostMapping("/tasks/{parent}/sub/create")
    public String addSubTask(Model model, @PathVariable("parent") UUID parent, @Valid SubTaskDTO subTaskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("Task", taskService.get(parent));
            model.addAttribute("page", taskService.get(parent).getTitle() + " ");
            return "task";
        }
        taskService.addSubTask(subTaskDTO);
        return "redirect:/tasks/" + parent;
    }


}
