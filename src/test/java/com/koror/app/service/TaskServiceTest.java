package com.koror.app.service;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.TaskRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskServiceTest {

    @Test(expected = WrongInputException.class)
    public void testNegativeAddTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.addTask(null);
    }

    @Test
    public void testPositiveAddTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        final Task task = new Task();
        taskService.addTask(task);
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeCompleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.completeTask(null);
    }

    @Test
    public void testPositiveCompleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        final Task task = new Task();
        taskService.addTask(task);
        taskService.completeTask(task);
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeDeleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.deleteTask("undefined task id");
    }

    @Test
    public void testPositiveDeleteTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        final Task task = new Task();
        taskService.addTask(task);
        taskService.deleteTask(task.getId());
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeUpdateTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        final Task task = new Task("text");
        taskService.addTask(task);
        taskService.updateTask(null);
    }

    @Test
    public void testPositiveUpdateTask() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        final Task task = new Task("text");
        taskService.addTask(task);
        task.setText("new text");
        taskService.updateTask(task);
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeSetGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        taskService.setGroupId(null);
    }

    @Test
    public void testPositiveSetGroupId() {
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository);
        final Task task = new Task("text");
        taskService.addTask(task);
        final Group group = new Group();
        task.setGroupId(group.getId());
        taskService.setGroupId(task);
    }

}