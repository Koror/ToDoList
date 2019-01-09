package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.TaskRepository;

import java.io.*;
import java.util.List;

public class TaskService implements ITaskRepository, IDataIO {

    private final TaskRepository taskRepository;

    private final String pathXml = "data/task/data_task.xml";

    private final String pathJson = "data/task/data_task.json";

    private final String pathBin = "data/task/data_task.tmp";

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.addTask(task);
    }

    @Override
    public void completeTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.completeTask(task);
    }

    @Override
    public Task deleteTask(final String id) throws WrongInputException {
        if (id == null || "".equals(id)) throw new WrongInputException("Wrong input");
        Task task = taskRepository.deleteTask(id);
        if (task == null) throw new WrongInputException("Wrong input");
        return task;
    }

    @Override
    public void updateTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.updateTask(task);
    }

    @Override
    public void clearTask() {
        taskRepository.clearTask();
    }

    @Override
    public void setGroupId(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.setGroupId(task);
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public Task getTaskByIndex(Integer index) {
        return taskRepository.getTaskByIndex(index);
    }

    @Override
    public void saveDataSerialization() throws IOException {
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(getTaskList());
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException {
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List tasks = (List) ois.readObject();
        for (Object task : tasks)
            if (task instanceof Task) addTask((Task) task);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final Task[] listTask = getTaskList().toArray(new Task[getTaskList().size()]);
        objectMapper.writeValue(new File(pathXml), listTask);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final Task[] listTask = objectMapper.readValue(new File(pathXml), Task[].class);
        for (Task task : listTask) addTask(task);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Task[] listTask = getTaskList().toArray(new Task[getTaskList().size()]);
        objectMapper.writeValue(new File(pathJson), listTask);
    }

    @Override
    public void loadDataJson() throws IOException{
        final ObjectMapper objectMapper = new ObjectMapper();
        final Task[] listTask = objectMapper.readValue(new File(pathJson), Task[].class);
        for (Task task : listTask) addTask(task);
    }

}
