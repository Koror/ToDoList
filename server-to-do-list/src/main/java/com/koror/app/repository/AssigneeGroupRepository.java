package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AssigneeGroupRepository extends AbstractRepository<AssigneeGroup> implements IAssigneeGroupRepository {

    @Override
    public void delete(String id, EntityManager entityManager) {
        AssigneeGroup entity = entityManager.find(AssigneeGroup.class, id);
        entityManager.remove(entity);
    }

    @Override
    public void deleteAssigneeByParam(String userId, String groupId, EntityManager entityManager) {
        Query deleteItemsQuery = entityManager.createQuery("DELETE AssigneeGroup ag WHERE ag.user.id =:inpUserId and at.group.id =:inpGroupId");
        deleteItemsQuery.setParameter("inpUserId", userId);
        deleteItemsQuery.setParameter("inpGroupId", groupId);
        deleteItemsQuery.executeUpdate();
    }

    @Override
    public AssigneeGroup getById(String id, EntityManager entityManager) {
        return entityManager.find(AssigneeGroup.class, id);
    }

    @Override
    public List<AssigneeGroup> getList(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AssigneeGroup> criteria = builder.createQuery(AssigneeGroup.class);
        criteria.from(AssigneeGroup.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId, EntityManager entityManager) {
        return entityManager.find(AssigneeGroup.class, userId);
    }

}
