package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;

public interface IAssigneeGroupRepository {

    void addAssignee(AssigneeGroup assigneeGroup);

    AssigneeGroup getAssigneeById(String id);

    AssigneeGroup getAssigneeByUserId(String userId);

}
