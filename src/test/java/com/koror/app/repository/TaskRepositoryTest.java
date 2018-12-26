package com.koror.app.repository;

import com.koror.app.entity.Task;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskRepositoryTest {

    private TaskRepository taskRepository;

    @Before
    public void before() {
        taskRepository = new TaskRepository();
    }

    @Test
    public void addTask() {
        Task task = new Task("testTask");
        taskRepository.addTask(task);
        assertNotNull(taskRepository.getTaskList().get(0));
    }

    @Test
    public void completeTask() {
    }

    @Test
    public void deleteTask() {
    }

    @Test
    public void updateTask() {
    }

    @Test
    public void clearTask() {
    }

    @Test
    public void setGroupId() {
    }

    @Test
    public void getTaskList() {
    }

    @Test
    public void getTaskMap() {
    }
}