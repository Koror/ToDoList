package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GroupRepository extends AbstractRepository<Group> implements IGroupRepository {


    @Override
    public void delete(String id, EntityManager entityManager) {
        Group entity = entityManager.find(Group.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Group getById(String id, EntityManager entityManager) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getList(EntityManager entityManager) {
        return entityManager.createQuery("FROM Group", Group.class).getResultList();
    }

    @Override
    public List<Group> getListGroupByUserId(final String userId, EntityManager entityManager) {
        List<Group> groupList = entityManager.createQuery("FROM Group e WHERE e.user.id = :userId", Group.class)
                .setParameter("userId", userId)
                .getResultList();
        return groupList;
    }
}
