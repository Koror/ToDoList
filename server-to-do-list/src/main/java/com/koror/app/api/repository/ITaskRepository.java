package com.koror.app.api.repository;

import com.koror.app.entity.Task;

import java.io.IOException;
import java.util.*;

public interface ITaskRepository extends IRepository<Task> {

    void completeTask(final Task task);

    void clearTask(List<Task> taskList);

    void setGroupId(final Task task, String idGroup);

    Task getTaskByIndex(Integer index);

}
