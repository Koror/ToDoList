package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;
import com.koror.app.util.DatabaseConfig;

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
        String query = "select p from " + DatabaseConfig.PREFIXDB + Group.class.getSimpleName() + " p";
        return (List<Group>) hibernateSession.createQuery(query).list();
    }
}
