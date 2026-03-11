package com.example.todoApp.controller;

import com.example.todoApp.dto.TaskRequest;
import com.example.todoApp.dto.TaskResponse;
import com.example.todoApp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        return service.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return service.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,
                                   @RequestBody TaskRequest request) {
        return service.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}
