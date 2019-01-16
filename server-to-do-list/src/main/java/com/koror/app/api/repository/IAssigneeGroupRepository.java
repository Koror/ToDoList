package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;

import java.util.List;

public interface IAssigneeGroupRepository extends IRepository<AssigneeGroup> {

    void deleteAssigneeByParam(String userId, String groupId);

    AssigneeGroup getAssigneeByUserId(String userId);

}
