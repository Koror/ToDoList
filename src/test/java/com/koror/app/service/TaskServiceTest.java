package com.koror.app.service;

import com.koror.app.error.WrongInputException;
import com.koror.app.repository.TaskRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskServiceTest {

    @Test(expected = WrongInputException.class)
    public void addTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.addTask(null);
    }

    @Test(expected = WrongInputException.class)
    public void completeTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.completeTask(null);
    }

    @Test
    public void deleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        assertNull(taskService.deleteTask(null));
    }

    @Test(expected = WrongInputException.class)
    public void updateTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.updateTask(null);
    }

    @Test(expected = WrongInputException.class)
    public void setGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.setGroupId(null);
    }
}