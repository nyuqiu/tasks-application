package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    private Task task1;
    private TaskDto taskDto;
    private List<Task> taskList;

    @Before
    public void setUp() throws Exception {
        task1 = new Task(1L, "test1", "test1");
        Task task2 = new Task(2L, "test2", "test2");
        taskList = new ArrayList<>(Arrays.asList(task1, task2));
        taskDto = new TaskDto(3L, "test3", "test3");
    }

    @Test
    public void mapToTask() {
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(mappedTask.getTitle(), taskDto.getTitle());
    }

    @Test
    public void mapToTaskDto() {
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task1);
        //Then
        assertEquals(mappedTaskDto.getTitle(), task1.getTitle());
    }

    @Test
    public void mapToTaskDtoList() {
        //When
        List<TaskDto> mappedListTaskDto = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(mappedListTaskDto.size(), taskList.size());
        assertEquals(mappedListTaskDto.get(0).getTitle(), taskList.get(0).getTitle());
        assertEquals(mappedListTaskDto.get(1).getTitle(), taskList.get(1).getTitle());
    }
}