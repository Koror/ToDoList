package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;

import java.util.*;

public class TaskRepository implements ITaskRepository {

    private final Map<String, Task> taskMap = new HashMap<>();

    public void addTask(final Task task) {
        taskMap.put(task.getId(), task);
    }

    public void completeTask(final Task task) {
        taskMap.get(task.getId()).setComplete();
    }

    public Task deleteTask(final String id) {
        return taskMap.remove(id);
    }

    public void updateTask(final Task task) {
        taskMap.put(task.getId(), task);
    }

    public void clearTask() {
        final Iterator<Task> taskIterator = taskMap.values().iterator();
        while (taskIterator.hasNext()) {
            if (taskIterator.next().getComplete())
                taskIterator.remove();
        }
    }

    public void setGroupId(final Task task) {
        taskMap.put(task.getId(), task);
    }

    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }

}

