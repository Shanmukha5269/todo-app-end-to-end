package com.example.todoApp.service;

import com.example.todoApp.dto.TaskRequest;
import com.example.todoApp.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest task);

    List<TaskResponse> getAllTasks();

    TaskResponse updateTask(Long id, TaskRequest task);

    void deleteTask(Long id);
}
