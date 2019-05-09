package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    public void shouldGetEmptyTaskList() throws Exception {
        //Given
        List<TaskDto> tasks = new ArrayList<>();
        when(taskController.getTasks()).thenReturn(tasks);
        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetListWithTwoTasks() throws Exception {
        //Given
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1L, "Test name", "Test content"));
        tasks.add(new TaskDto(2L, "Test name 2", "Test content 2"));
        when(taskController.getTasks()).thenReturn(tasks);
        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test name")))
                .andExpect(jsonPath("$[0].content", is("Test content")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test name 2")))
                .andExpect(jsonPath("$[1].content", is("Test content 2")));


    }

    @Test
    public void shouldGetOneTaskById() throws Exception {
        //Given
        TaskDto task = new TaskDto(1, "Test name", "Test content");
        when(taskController.getTask(1L)).thenReturn(task);
        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test name")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void shouldDeleteTaskById() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldReplaceTaskWithAnotherById() throws Exception {
        //Given
        TaskDto newTask = new TaskDto(1L, "New test name", "New test content");
        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(newTask);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newTask);
        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("New test name")))
                .andExpect(jsonPath("$.content", is("New test content")));
    }

    @Test
    public void shouldCreateNewTask() throws Exception {
        //Given
        TaskDto newTask = new TaskDto(1L, "New test name", "New test content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newTask);
        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}