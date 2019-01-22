package com.koror.app.repository;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.entity.Session;
import com.koror.app.util.DatabaseConfig;

import java.util.List;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    public void delete(String id) {
        hibernateSession.beginTransaction();
        Session entity = hibernateSession.get(Session.class, id);
        hibernateSession.delete(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public Session getById(String id) {
        hibernateSession.beginTransaction();
        Session entity = hibernateSession.get(Session.class, id);
        hibernateSession.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Session> getList() {
        String query = "select p from " + DatabaseConfig.PREFIXDB + Session.class.getSimpleName() + " p";
        return (List<Session>) hibernateSession.createQuery(query).list();
    }

}
