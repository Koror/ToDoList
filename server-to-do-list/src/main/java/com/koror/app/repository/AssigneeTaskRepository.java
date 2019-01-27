package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeTask;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AssigneeTaskRepository extends AbstractRepository<AssigneeTask> implements IAssigneeTaskRepository {

    @Override
    public void delete(String id, EntityManager entityManager) {
        AssigneeTask entity = entityManager.find(AssigneeTask.class, id);
        entityManager.remove(entity);
    }

    @Override
    public void deleteAssigneeByParam(String userId, String taskId, EntityManager entityManager) {
        Query deleteItemsQuery = entityManager.createQuery("DELETE AssigneeTask at WHERE at.user.id =:inpUserId and at.task.id =:inpTaskId");
        deleteItemsQuery.setParameter("inpUserId", userId);
        deleteItemsQuery.setParameter("inpTaskId", taskId);
        deleteItemsQuery.executeUpdate();
    }

    @Override
    public AssigneeTask getById(String id, EntityManager entityManager) {
        return entityManager.find(AssigneeTask.class, id);
    }

    @Override
    public List<AssigneeTask> getList(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AssigneeTask> criteria = builder.createQuery(AssigneeTask.class);
        criteria.from(AssigneeTask.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId, EntityManager entityManager) {
        return entityManager.find(AssigneeTask.class, userId);
    }
}
