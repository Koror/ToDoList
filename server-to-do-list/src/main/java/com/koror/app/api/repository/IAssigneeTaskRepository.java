package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeTask;

import java.util.List;

public interface IAssigneeTaskRepository extends IRepository<AssigneeTask>{

    AssigneeTask getAssigneeByUserId(String userId);

    void delete(String id);

    AssigneeTask getById(String id);

    List<AssigneeTask> getList();

}
