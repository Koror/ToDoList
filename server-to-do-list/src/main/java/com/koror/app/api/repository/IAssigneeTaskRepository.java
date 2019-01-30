package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeTask;

import javax.persistence.EntityManager;
import java.util.List;

public interface IAssigneeTaskRepository extends IRepository<AssigneeTask>{

    AssigneeTask getAssigneeByUserId(String userId);

    void delete(String id);

    void deleteAssigneeByParam(String userId, String taskId);

    AssigneeTask getById(String id);

    List<AssigneeTask> getList();

}
