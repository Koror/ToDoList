package com.koror.app.repository;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TaskRepositoryTest {

    @Test
    public void addTask() throws WrongInputException {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        assertNotNull(taskRepository.getTaskList().get(0).getId());
    }

    @Test
    public void completeTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().get(task.getId()).setComplete();
        assertTrue(taskRepository.getTaskMap().get(task.getId()).getComplete());
    }

    @Test(expected = NullPointerException.class)
    public void deleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().remove(task.getId());
        task = taskRepository.getTaskMap().get(task.getId());
        task.getId();
    }

    @Test
    public void updateTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        final String id = task.getId();
        task = new Task("new test task");
        taskRepository.getTaskMap().put(task.getId(), task);
        assertFalse(id.equals(taskRepository.getTaskMap().get(task.getId()).getId()));
    }

    @Test(expected = NullPointerException.class)
    public void clearTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().get(task.getId()).setComplete();
        taskRepository.clearTask();
        task = taskRepository.getTaskMap().get(task.getId());
        task.getId();
    }

    @Test
    public void setGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        final Group group = new Group("test group");
        final String groupId = group.getId();
        task.setGroupId(group.getId());
        taskRepository.getTaskMap().put(task.getId(), task);
        task = taskRepository.getTaskMap().get(task.getId());
        assertTrue(groupId.endsWith(task.getGroupId()));
    }

    @Test
    public void getTaskList() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        assertNotNull(taskRepository.getTaskMap().values());
    }

    @Test
    public void getTaskMap() {
        final TaskRepository taskRepository = new TaskRepository();
        assertNotNull(taskRepository.getTaskMap());
    }
}