package com.koror.app.api.repository;

import com.koror.app.entity.Task;

import java.util.*;

public interface ITaskRepository {

    void addTask(final Task task);

    void completeTask(final Task task);

    Task deleteTask(final String id);

    void updateTask(final Task task);

    void clearTask();

    void setGroupId(final Task task);

    List<Task> getTaskList();

    Task getTaskById(final String id);

    Task getTaskByIndex(Integer index);

}
