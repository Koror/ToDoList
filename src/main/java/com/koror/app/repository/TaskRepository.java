package com.koror.app.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class TaskRepository implements ITaskRepository {

    private final Map<String, Task> taskMap = new HashMap<>();

    private final String pathXml = "data/task/data_task.xml";

    private final String pathJson = "data/task/data_task.json";

    private final String pathBin = "data/task/data_task.tmp";

    @Override
    public void addTask(final Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public void completeTask(final Task task) {
        taskMap.get(task.getId()).setComplete();
    }

    @Override
    public Task deleteTask(final String id) {
        return taskMap.remove(id);
    }

    @Override
    public void updateTask(final Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public void clearTask() {
        final Iterator<Task> taskIterator = taskMap.values().iterator();
        while (taskIterator.hasNext()) {
            if (taskIterator.next().getComplete())
                taskIterator.remove();
        }
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

    @Override
    public void setGroupId(final Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public Task getTaskById(final String id) {
        return taskMap.get(id);
    }

    @Override
    public Task getTaskByIndex(Integer index) {
        return getTaskList().get(index);
    }

    @Override
    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

}

