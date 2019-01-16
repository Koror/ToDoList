package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;

import java.util.List;

public interface IAssigneeTaskRepository extends IRepository<AssigneeTask>{

    void deleteAssigneeByParam(String userId, String taskId);

    AssigneeTask getAssigneeByUserId(String userId);

}
