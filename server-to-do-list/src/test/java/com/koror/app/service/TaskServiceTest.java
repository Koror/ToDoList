package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeTaskRepository;
import com.koror.app.repository.TaskRepository;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class TaskServiceTest {

//    @Test(expected = WrongInputException.class)
//    public void testNegativeAddTask() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        taskService.add(null);
//    }
//
//    @Test
//    public void testPositiveAddTask() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        final Task task = new Task();
//        taskService.add(task);
//    }
//
//    @Test(expected = WrongInputException.class)
//    public void testNegativeCompleteTask() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        taskService.completeTask(null);
//    }
//
//    @Test
//    public void testPositiveCompleteTask() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        final Task task = new Task();
//        taskService.add(task);
//        taskService.completeTask(task);
//    }
////
////    @Test(expected = WrongInputException.class)
////    public void testNegativeDeleteTask() {
////        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
////        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
////        final TaskRepository taskRepository = new TaskRepository();
////        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
////        taskService.delete("undefined task id");
////    }
//
//    @Test
//    public void testPositiveDeleteTask() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        final Task task = new Task();
//        taskService.add(task);
//        taskService.delete(task.getId());
//    }
//
//    @Test(expected = WrongInputException.class)
//    public void testNegativeUpdateTask() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        final Task task = new Task("text");
//        taskService.add(task);
//        taskService.update(null);
//    }
//
//    @Test
//    public void testPositiveUpdateTask() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        final Task task = new Task("text");
//        taskService.add(task);
//        task.setName("new text");
//        taskService.update(task);
//    }
//
//    @Test(expected = WrongInputException.class)
//    public void testNegativeSetGroupId() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        taskService.setGroupId(null, null);
//    }
//
//    @Test
//    public void testPositiveSetGroupId() {
//        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
//        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
//        final TaskRepository taskRepository = new TaskRepository();
//        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
//        final Task task = new Task("text");
//        taskService.add(task);
//        final Group group = new Group();
//        task.setGroupId(group.getId());
//        taskService.setGroupId(task, null);
//    }

}