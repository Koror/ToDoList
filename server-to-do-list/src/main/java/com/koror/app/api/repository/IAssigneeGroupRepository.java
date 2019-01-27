package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;

import javax.persistence.EntityManager;
import java.util.List;

public interface IAssigneeGroupRepository extends IRepository<AssigneeGroup>{

    AssigneeGroup getAssigneeByUserId(String userId, EntityManager entityManager);

    void delete(String id, EntityManager entityManager);

    void deleteAssigneeByParam(String userId, String groupId, EntityManager entityManager);

    AssigneeGroup getById(String id, EntityManager entityManager);

    List<AssigneeGroup> getList(EntityManager entityManager);

}
