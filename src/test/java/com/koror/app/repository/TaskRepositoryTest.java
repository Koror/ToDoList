package com.koror.app.repository;

import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskRepositoryTest {

    @Test
    public void addTask() throws WrongInputException {
        TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("testTask");
        taskRepository.addTask(task);
        assertNotNull(taskRepository.getTaskList().get(0).getId());
    }

    @Test
    public void completeTask() {
        TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("testTask");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().get(task.getId()).setComplete();
        assertTrue( taskRepository.getTaskMap().get(task.getId()).getComplete());
    }

    @Test (expected = NullPointerException.class)
    public void deleteTask() {
        TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("testTask");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().remove(task.getId());
        taskRepository.getTaskMap().get(0);
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