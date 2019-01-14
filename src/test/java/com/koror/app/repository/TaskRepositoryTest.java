package com.koror.app.repository;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskRepositoryTest {

    @Test
    public void testPositiveAddTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.add(task);
        assertNotNull(taskRepository.getTaskByIndex(0).getId());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeAddTask() {
        final TaskRepository taskRepository = new TaskRepository();
        assertNotNull(taskRepository.getTaskByIndex(0).getId());
    }

    @Test
    public void testPositiveCompleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.add(task);
        taskRepository.getById(task.getId()).setComplete(true);
        assertTrue(taskRepository.getById(task.getId()).getComplete());
    }

    @Test
    public void testNegativeCompleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.add(task);
        assertFalse(taskRepository.getById(task.getId()).getComplete());
    }

    @Test
    public void testPositiveDeleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.add(task);
        taskRepository.delete(task.getId());
        assertTrue(taskRepository.getList().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testNegativeDeleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.add(task);
        taskRepository.delete(task.getId());
        task = taskRepository.getById(task.getId());
        task.getId();
    }

    @Test
    public void testPositiveUpdateTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.add(task);
        final String id = task.getId();
        task = new Task("new test task");
        taskRepository.update(task);
        assertFalse(id.equals(taskRepository.getById(task.getId()).getId()));
    }

    @Test
    public void testPositiveClearTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.add(task);
        taskRepository.getById(task.getId()).setComplete(true);
        taskRepository.clearTask(taskRepository.getList());
        assertTrue(taskRepository.getList().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testNegativeClearTask() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.add(task);
        taskRepository.getById(task.getId()).setComplete(true);
        taskRepository.clearTask(taskRepository.getList());
        task = taskRepository.getById(task.getId());
        task.getId();
    }

    @Test
    public void testPositiveSetGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.add(task);
        final Group group = new Group("test group");
        final String groupId = group.getId();
        task.setGroupId(group.getId());
        taskRepository.add(task);
        task = taskRepository.getById(task.getId());
        assertEquals(groupId, task.getGroupId());
    }

    @Test
    public void testNegativeSetGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        Task task = new Task("test task");
        taskRepository.add(task);
        taskRepository.add(task);
        task = taskRepository.getById(task.getId());
        assertNull(task.getGroupId());
    }

    @Test
    public void testPositiveGetTaskList() {
        final TaskRepository taskRepository = new TaskRepository();
        final Task task = new Task("test task");
        taskRepository.add(task);
        assertFalse(taskRepository.getList().isEmpty());
    }

    @Test
    public void testNegativeGetTaskList() {
        final TaskRepository taskRepository = new TaskRepository();
        assertTrue(taskRepository.getList().isEmpty());
    }

}