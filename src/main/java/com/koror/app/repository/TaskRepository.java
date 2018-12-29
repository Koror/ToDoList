package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class TaskRepository implements ITaskRepository {

    private final Map<String, Task> taskMap = new HashMap<>();

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
    public void saveData() {
        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data_task.tmp"))) {
            final File f = new File("data_task.tmp");
            if (f.isFile())
                f.delete();
            oos.writeObject(getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data_task.tmp"))) {
            final List tasks = (List) ois.readObject();
            for (Object task : tasks)
                if (task instanceof Task)
                    addTask((Task) task);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

