package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeTask;

import javax.persistence.EntityManager;
import java.util.List;

public interface IAssigneeTaskRepository extends IRepository<AssigneeTask>{

    AssigneeTask getAssigneeByUserId(String userId, EntityManager entityManager);

    void delete(String id, EntityManager entityManager);

    void deleteAssigneeByParam(String userId, String taskId, EntityManager entityManager);

    AssigneeTask getById(String id, EntityManager entityManager);

    List<AssigneeTask> getList(EntityManager entityManager);

}
