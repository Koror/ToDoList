package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;

import javax.persistence.EntityManager;
import java.util.List;

public interface IAssigneeGroupRepository extends IRepository<AssigneeGroup>{

    AssigneeGroup getAssigneeByUserId(String userId);

    void delete(String id);

    void deleteAssigneeByParam(String userId, String groupId);

    AssigneeGroup getById(String id);

    List<AssigneeGroup> getList();

}
