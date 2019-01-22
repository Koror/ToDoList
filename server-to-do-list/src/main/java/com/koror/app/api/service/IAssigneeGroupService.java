package com.koror.app.api.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;

public interface IAssigneeGroupService extends IAssigneeGroupRepository {

    void deleteAssigneeByParam(String userId, String groupId);

}
