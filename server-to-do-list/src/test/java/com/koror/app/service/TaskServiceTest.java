package com.koror.app.service;

import com.koror.app.App;
import com.koror.app.api.controller.IBootstrap;
import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.service.ITaskService;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import org.junit.Test;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.io.IOException;
import java.util.List;

public class TaskServiceTest {

    @Test
    public void test() throws IOException, ReflectiveOperationException {
        SeContainer seContainer = SeContainerInitializer.newInstance().addPackages(App.class).initialize();
        IBootstrap bootstrap = seContainer.select(IBootstrap.class).get();
        ITaskService taskService = seContainer.select(ITaskService.class).get();
        IAssigneeTaskRepository assigneeTaskRepository = seContainer.select(IAssigneeTaskRepository.class).get();
        List<Task> list = taskService.getList();
        Task task = list.get(0);
        User user = new User();
        user.setId("15fd2cf8-6188-4378-9126-c26643feb691");
        taskService.delete(task);
        list.get(0);
        //task.getName();
    }

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
//        taskService.setGroup(null, null);
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
//        task.setGroup(group.getId());
//        taskService.setGroup(task, null);
//    }

}