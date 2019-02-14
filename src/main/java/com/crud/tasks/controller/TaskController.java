package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @GetMapping
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    @GetMapping("{id}")
    public TaskDto getTask(@PathVariable("id") Long taskId){
        return new TaskDto(1L,"test title", "test_content");
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable("id") Long taskId){

    }

    @PutMapping("{id}")
    public TaskDto updateTask(@PathVariable("id") Long taskId, @RequestBody String taskDto){
        return new TaskDto(1L, "Edited test file", "Test content");
    }

    @PostMapping()
    public void createTask(@RequestBody String taskDto){

    }
}
