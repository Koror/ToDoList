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

    @Test(expected = WrongInputException.class)
    public void testNegativeAddTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        taskService.add(null);
    }

    @Test
    public void testPositiveAddTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task();
        taskService.add(task);
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeCompleteTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        taskService.completeTask(null);
    }

    @Test
    public void testPositiveCompleteTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task();
        taskService.add(task);
        taskService.completeTask(task);
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeDeleteTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        taskService.delete("undefined task id");
    }

    @Test
    public void testPositiveDeleteTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task();
        taskService.add(task);
        taskService.delete(task.getId());
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeUpdateTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task("text");
        taskService.add(task);
        taskService.update(null);
    }

    @Test
    public void testPositiveUpdateTask() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task("text");
        taskService.add(task);
        task.setName("new text");
        taskService.update(task);
    }

    @Test(expected = WrongInputException.class)
    public void testNegativeSetGroupId() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        taskService.setGroupId(null);
    }

    @Test
    public void testPositiveSetGroupId() {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task("text");
        taskService.add(task);
        final Group group = new Group();
        task.setGroupId(group.getId());
        taskService.setGroupId(task);
    }

    @Test
    public void saveDataSerialization() throws IOException {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task();
        taskService.add(task);

        final String pathBin = "test_data/task/data_task.tmp";
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(taskService.getList());
        File checkFile = new File("test_data/task/data_task.tmp");
        assertTrue(checkFile.exists());
    }

    @Test
    public void loadDataSerialization() throws IOException, ClassNotFoundException {
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);

        final String pathBin = "test_data/task/data_task.tmp";
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List tasks = (List) ois.readObject();
        for (Object task : tasks)
            if (task instanceof Task) taskService.add((Task) task);
        assertNotNull(taskService.getList().get(0).getId());
    }

    @Test
    public void saveDataXml() throws IOException{
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task();
        taskService.add(task);

        final String pathXml = "test_data/task/data_task.xml";
        final File f = new File(pathXml);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final Task[] listTask = taskService.getList().toArray(new Task[taskService.getList().size()]);
        objectMapper.writeValue(new File(pathXml), listTask);
        File checkFile = new File("test_data/task/data_task.xml");
        assertTrue(checkFile.exists());
    }

    @Test
    public void loadDataXml() throws IOException{
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);

        final String pathXml = "test_data/task/data_task.xml";
        final ObjectMapper objectMapper = new XmlMapper();
        final Task[] listTask = objectMapper.readValue(new File(pathXml), Task[].class);
        for (Task task : listTask) taskService.add(task);
    }

    @Test
    public void saveDataJson() throws IOException{
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);
        final Task task = new Task();
        taskService.add(task);

        final String pathJson = "data/task/data_task.json";
        final File f = new File(pathJson);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Task[] listTask = taskService.getList().toArray(new Task[taskService.getList().size()]);
        objectMapper.writeValue(new File(pathJson), listTask);
    }

    @Test
    public void loadDataJson() throws IOException{
        final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();
        final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        final TaskRepository taskRepository = new TaskRepository();
        final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);

        final String pathJson = "data/task/data_task.json";
        final ObjectMapper objectMapper = new ObjectMapper();
        final Task[] listTask = objectMapper.readValue(new File(pathJson), Task[].class);
        for (Task task : listTask) taskService.add(task);
    }
}