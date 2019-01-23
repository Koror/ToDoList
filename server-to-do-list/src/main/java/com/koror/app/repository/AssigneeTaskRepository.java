package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeTask;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<AssigneeTask> criteria = builder.createQuery(AssigneeTask.class);
        criteria.from(AssigneeTask.class);
        return hibernateSession.createQuery(criteria).getResultList();
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        hibernateSession.beginTransaction();
        AssigneeTask entity = hibernateSession.get(AssigneeTask.class, userId);
        hibernateSession.getTransaction().commit();
        return entity;
    }
}
