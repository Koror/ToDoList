package com.koror.app.api.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;

import javax.persistence.EntityManager;
import java.util.List;

public interface IAssigneeGroupService {

    AssigneeGroup getAssigneeByUserId(String userId);

    void delete(String id);

    AssigneeGroup getById(String id);

    List<AssigneeGroup> getList();

    void deleteAssigneeByParam(String userId, String groupId);

}
