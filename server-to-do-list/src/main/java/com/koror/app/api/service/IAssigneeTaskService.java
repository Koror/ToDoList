package com.koror.app.api.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;

public interface IAssigneeTaskService extends IAssigneeTaskRepository {

    void deleteAssigneeByParam(String userId, String groupId);

}
