package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.util.DatabaseConfig;

import java.util.List;

public class AssigneeTaskRepository extends AbstractRepository<AssigneeTask> implements IAssigneeTaskRepository {

    @Override
    public void delete(String id) {
        hibernateSession.beginTransaction();
        AssigneeTask entity = hibernateSession.get(AssigneeTask.class, id);
        hibernateSession.delete(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public AssigneeTask getById(String id) {
        hibernateSession.beginTransaction();
        AssigneeTask entity = hibernateSession.get(AssigneeTask.class, id);
        hibernateSession.getTransaction().commit();
        return entity;
    }

    @Override
    public List<AssigneeTask> getList() {
        String query = "select p from " + DatabaseConfig.PREFIXDB + AssigneeTask.class.getSimpleName() + " p";
        return (List<AssigneeTask>) hibernateSession.createQuery(query).list();
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        hibernateSession.beginTransaction();
        AssigneeTask entity = hibernateSession.get(AssigneeTask.class, userId);
        hibernateSession.getTransaction().commit();
        return entity;
    }
}
