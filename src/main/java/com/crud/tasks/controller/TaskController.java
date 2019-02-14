package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
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
