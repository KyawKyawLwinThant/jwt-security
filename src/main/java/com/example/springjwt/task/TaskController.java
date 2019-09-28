package com.example.springjwt.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private final TaskRepository taskRepository;

    @PostMapping
    public void createTask(@RequestBody Task task){
        this.taskRepository.save(task);
    }

    @GetMapping
    public List<Task> showAllTasks(){
        return this.taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public void updateTasks(@RequestBody Task task,@PathVariable long id){
            Task existingTask=this.taskRepository.findById(id).get();
            existingTask.setDescription(task.getDescription());
            this.taskRepository.save(existingTask);
    }
    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable long id){
        this.taskRepository.delete(this.taskRepository.getOne(id));
    }



}
