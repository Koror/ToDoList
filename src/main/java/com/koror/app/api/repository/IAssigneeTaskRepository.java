package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeTask;

public interface IAssigneeTaskRepository {

    void addAssignee(AssigneeTask assigneeTask);

    AssigneeTask getAssigneeById(String id);

    AssigneeTask getAssigneeByUserId(String userId);

}
