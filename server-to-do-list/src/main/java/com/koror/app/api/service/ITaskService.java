package com.koror.app.api.service;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.WrongInputException;

import java.util.List;

public interface ITaskService extends ITaskRepository {

    void completeTask(final Task task) throws WrongInputException;

    void clearTask(List<Task> taskList);

    void setGroup(final Task task, Group group) throws WrongInputException;

    List<Task> getListTaskByUser(User user);
}
