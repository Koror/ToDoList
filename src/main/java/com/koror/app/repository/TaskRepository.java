package com.koror.app.repository;

import com.koror.app.entity.Task;

import java.util.*;

public class TaskRepository {

    private final Map<String, Task> taskMap = new HashMap<>();

    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void completeTask(Task task) {
        taskMap.get(task.getId()).complete();

    }

    public void deleteTask(String id) {
        taskMap.remove(id);
    }

    public void updateTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void clearTask() {
        Iterator<Task> taskIterator = taskMap.values().iterator();
        while (taskIterator.hasNext()) {
            if (taskIterator.next().isComplete())
                taskIterator.remove();
        }
    }

    public void setGroupId(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void readAll() {
        int index = 0;
        for (Task task : taskMap.values()) {
            System.out.println("   " + index + " [" + task.toString() + "]");
            index++;
        }
    }

    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }
}

