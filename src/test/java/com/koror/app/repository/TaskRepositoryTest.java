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
    public void testPositiveAddTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        assertNotNull(taskRepository.getTaskList().get(0).getId());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeAddTask() {
        final TaskRepository taskRepository = new TaskRepository();
        assertNotNull(taskRepository.getTaskList().get(0).getId());
    }

    @Test
    public void testPositiveCompleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().get(task.getId()).setComplete();
        assertTrue(taskRepository.getTaskMap().get(task.getId()).getComplete());
    }

    @Test
    public void testNegativeCompleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        assertFalse(taskRepository.getTaskMap().get(task.getId()).getComplete());
    }

    @Test
    public void testPositiveDeleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().remove(task.getId());
        assertTrue(taskRepository.getTaskMap().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testNegativeDeleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().remove(task.getId());
        task = taskRepository.getTaskMap().get(task.getId());
        task.getId();
    }

    @Test
    public void testPositiveUpdateTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        final String id = task.getId();
        task = new Task("new test task");
        taskRepository.getTaskMap().put(task.getId(), task);
        assertFalse(id.equals(taskRepository.getTaskMap().get(task.getId()).getId()));
    }

    @Test
    public void testPositiveClearTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().get(task.getId()).setComplete();
        taskRepository.clearTask();
        assertTrue(taskRepository.getTaskMap().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testNegativeClearTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().get(task.getId()).setComplete();
        taskRepository.clearTask();
        task = taskRepository.getTaskMap().get(task.getId());
        task.getId();
    }

    @Test
    public void testPositiveSetGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        final Group group = new Group("test group");
        final String groupId = group.getId();
        task.setGroupId(group.getId());
        taskRepository.getTaskMap().put(task.getId(), task);
        task = taskRepository.getTaskMap().get(task.getId());
        assertEquals(groupId, task.getGroupId());
    }

    @Test
    public void testNegativeSetGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.addTask(task);
        taskRepository.getTaskMap().put(task.getId(), task);
        task = taskRepository.getTaskMap().get(task.getId());
        assertNull(task.getGroupId());
    }

    @Test
    public void testPositiveGetTaskList() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.addTask(task);
        assertFalse(taskRepository.getTaskMap().values().isEmpty());
    }

    @Test
    public void testNegativeGetTaskList() {
        final TaskRepository taskRepository = new TaskRepository();
        assertTrue(taskRepository.getTaskMap().values().isEmpty());
    }

    @Test
    public void testPositiveGetTaskMap() {
        final TaskRepository taskRepository = new TaskRepository();
        assertNotNull(taskRepository.getTaskMap());
    }

    @Test(expected = NullPointerException.class)
    public void testNegativeGetTaskMap() {
        final TaskRepository taskRepository = null;
        assertNull(taskRepository.getTaskMap());
    }
}