package com.koror.app.api.repository;

import com.koror.app.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task>{

    void delete(String id);

    Task getById(String id);

    List<Task> getList();

}
