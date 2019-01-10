package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;

import java.util.List;

public interface IAssigneeGroupRepository {

    void addAssignee(AssigneeGroup assigneeGroup);

    void deleteAssignee(String id);

    void deleteAssigneeByParam(String userId, String groupId);

    AssigneeGroup getAssigneeById(String id);

    AssigneeGroup getAssigneeByUserId(String userId);

    List<AssigneeGroup> getAssigneeGroupList();

}
