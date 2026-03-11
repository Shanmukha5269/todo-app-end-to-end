package com.example.todoApp.service;

import com.example.todoApp.dto.TaskRequest;
import com.example.todoApp.dto.TaskResponse;
import com.example.todoApp.exception.ResourceNotFoundException;
import com.example.todoApp.model.Task;
import com.example.todoApp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private TaskResponse mapToResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.isCompleted());

        return response;
    }

    public TaskResponse createTask(TaskRequest request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(false);

        Task saved = taskRepository.save(task);

        return mapToResponse(saved);
    }

    public List<TaskResponse> getAllTasks() {

        List<Task> tasks = taskRepository.findAll();
        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : tasks) {
            responses.add(mapToResponse(task));
        }

        return responses;
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.getCompleted());

        Task updated = taskRepository.save(task);

        return mapToResponse(updated);
    }

    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}
