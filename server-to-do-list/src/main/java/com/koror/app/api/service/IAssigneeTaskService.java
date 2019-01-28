package com.koror.app.api.service;

import com.koror.app.entity.AssigneeTask;

import java.util.List;

public interface IAssigneeTaskService {

    AssigneeTask getAssigneeByUserId(String userId);

    void delete(String id);

    AssigneeTask getById(String id);

    List<AssigneeTask> getList();

    void deleteAssigneeByParam(String userId, String groupId);

}
