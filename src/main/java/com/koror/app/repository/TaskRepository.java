package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;

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

    public void saveData(){

    }

    public void loadData(){

    }
    
    @Override
    public void setGroupId(final Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public Task getTaskById(final String id){
         return taskMap.get(id);
    }

    @Override
    public Task getTaskByIndex(Integer index){
        return getTaskList().get(index);
    }

    @Override
    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

}

