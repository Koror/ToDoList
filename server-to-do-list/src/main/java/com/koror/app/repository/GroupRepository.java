package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GroupRepository extends AbstractRepository<Group> implements IGroupRepository {

    @Override
    public void delete(String id) {
        hibernateSession.beginTransaction();
        Group entity = hibernateSession.get(Group.class, id);
        hibernateSession.delete(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public Group getById(String id) {
        hibernateSession.beginTransaction();
        Group entity = hibernateSession.get(Group.class, id);
        hibernateSession.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Group> getList() {
        CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Group> criteria = builder.createQuery(Group.class);
        criteria.from(Group.class);
        return hibernateSession.createQuery(criteria).getResultList();
    }
}
