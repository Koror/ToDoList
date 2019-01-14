package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;

import java.util.*;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void completeTask(final Task task) {
        mapEntity.get(task.getId()).setComplete(true);
    }

    @Override
    public void clearTask(List<Task> taskList) {
        for (Task task : taskList) {
            if (task.getComplete())
                delete(task.getId());
        }
    }

    @Override
    public void setGroupId(final Task task) {
        mapEntity.put(task.getId(), task);
    }

    @Override
    public Task getTaskByIndex(Integer index) {
        return getList().get(index);
    }

}

