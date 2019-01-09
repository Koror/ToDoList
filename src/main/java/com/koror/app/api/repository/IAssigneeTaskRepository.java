package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;

import java.util.List;

public interface IAssigneeTaskRepository {

    void addAssignee(AssigneeTask assigneeTask);

    void deleteAssignee(String id);

    AssigneeTask getAssigneeById(String id);

    AssigneeTask getAssigneeByUserId(String userId);

    List<AssigneeTask> getAssigneeTaskList();

}
