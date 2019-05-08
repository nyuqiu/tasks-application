package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTestSuite {

    @Autowired
    private TaskRepository taskRepository;

    private Task task1 = new Task();
    private Task task2 = new Task();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() {
        //When
        taskRepository.save(task1);
        long task1Id = task1.getId();
        taskRepository.save(task2);
        long task2Id = task2.getId();
        List<Task> tasks = taskRepository.findAll();

        //Then
        Assert.assertEquals(2, tasks.size());
        //CleanUp
        try {
            taskRepository.deleteById(task1Id);
            taskRepository.deleteById(task2Id);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void findOne() {
        //When
        taskRepository.save(task1);
        long task1Id = task1.getId();
        Task foundTask = taskRepository.findOne(task1Id);

        //Then
        Assert.assertEquals(task1.getId(), foundTask.getId());

        //CleanUp
        try {
            taskRepository.deleteById(task1Id);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void save() {
        //When
        taskRepository.save(task1);
        long task1Id = task1.getId();
        Task foundTask = taskRepository.findOne(task1Id);

        //Then
        Assert.assertNotNull(foundTask);

        //CleanUp
        try {
            taskRepository.deleteById(task1Id);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void findById() {
        //When
        taskRepository.save(task1);
        long task1Id = task1.getId();
        Optional<Task> foundTask = taskRepository.findById(task1Id);

        //Then
        Assert.assertTrue(foundTask.isPresent());

        //CleanUp
        try {
            taskRepository.deleteById(task1Id);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void deleteById() {
        //When
        taskRepository.save(task1);
        long task1Id = task1.getId();
        taskRepository.deleteById(task1Id);
        Optional<Task> foundTask = taskRepository.findById(task1Id);

        //Then
        Assert.assertFalse(foundTask.isPresent());
    }

    @Test
    public void count() {
        //When
        taskRepository.save(task1);
        long task1Id = task1.getId();
        taskRepository.save(task2);
        long task2Id = task2.getId();
        long count = taskRepository.count();

        //Then
        Assert.assertEquals(2, count);
        //CleanUp
        try {
            taskRepository.deleteById(task1Id);
            taskRepository.deleteById(task2Id);
        } catch (Exception e) {
            //do nothing
        }
    }
}