package com.koror.app.api.service;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ITaskService {

    void add(@Nullable Task entity, @Nullable User user);

    void add(@Nullable Task entity);

    void delete(@Nullable Task task);

    void update(@Nullable final Task entity);

    Task getById(@Nullable String id);

    List<Task> getList();

    List<Task> getListTaskByUserId(@Nullable String userId);

    List<Task> getListTaskByGroupId(@Nullable String groupId);

    void completeTask(@Nullable final Task task) throws WrongInputException;

    void clearTask(@Nullable List<Task> taskList);

    void setGroup(@Nullable final Task task, @Nullable Group group) throws WrongInputException;

    void linkToTask(@Nullable User user, @Nullable Task task);

}
