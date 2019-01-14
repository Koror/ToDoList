package com.koror.app.repository;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskRepositoryTest {

//    @Test
//    public void testPositiveAddTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.addTask(task);
//        assertNotNull(taskRepository.getTaskByIndex(0).getId());
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testNegativeAddTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        assertNotNull(taskRepository.getTaskByIndex(0).getId());
//    }
//
//    @Test
//    public void testPositiveCompleteTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.addTask(task);
//        taskRepository.getTaskById(task.getId()).setComplete();
//        assertTrue(taskRepository.getTaskById(task.getId()).getComplete());
//    }
//
//    @Test
//    public void testNegativeCompleteTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.addTask(task);
//        assertFalse(taskRepository.getTaskById(task.getId()).getComplete());
//    }
//
//    @Test
//    public void testPositiveDeleteTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.addTask(task);
//        taskRepository.deleteTask(task.getId());
//        assertTrue(taskRepository.getTaskList().isEmpty());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testNegativeDeleteTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.addTask(task);
//        taskRepository.deleteTask(task.getId());
//        task = taskRepository.getTaskById(task.getId());
//        task.getId();
//    }
//
//    @Test
//    public void testPositiveUpdateTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.addTask(task);
//        final String id = task.getId();
//        task = new Task("new test task");
//        taskRepository.updateTask(task);
//        assertFalse(id.equals(taskRepository.getTaskById(task.getId()).getId()));
//    }
//
//    @Test
//    public void testPositiveClearTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.addTask(task);
//        taskRepository.getTaskById(task.getId()).setComplete();
//        taskRepository.clearTask(taskRepository.getTaskList());
//        assertTrue(taskRepository.getTaskList().isEmpty());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testNegativeClearTask() {
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.addTask(task);
//        taskRepository.getTaskById(task.getId()).setComplete();
//        taskRepository.clearTask(taskRepository.getTaskList());
//        task = taskRepository.getTaskById(task.getId());
//        task.getId();
//    }
//
//    @Test
//    public void testPositiveSetGroupId() {
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.addTask(task);
//        final Group group = new Group("test group");
//        final String groupId = group.getId();
//        task.setGroupId(group.getId());
//        taskRepository.addTask(task);
//        task = taskRepository.getTaskById(task.getId());
//        assertEquals(groupId, task.getGroupId());
//    }
//
//    @Test
//    public void testNegativeSetGroupId() {
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.addTask(task);
//        taskRepository.addTask(task);
//        task = taskRepository.getTaskById(task.getId());
//        assertNull(task.getGroupId());
//    }
//
//    @Test
//    public void testPositiveGetTaskList() {
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.addTask(task);
//        assertFalse(taskRepository.getTaskList().isEmpty());
//    }
//
//    @Test
//    public void testNegativeGetTaskList() {
//        final TaskRepository taskRepository = new TaskRepository();
//        assertTrue(taskRepository.getTaskList().isEmpty());
//    }

}