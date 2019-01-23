package com.koror.app.repository;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.entity.Session;
import com.koror.app.util.AppConfig;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Session> criteria = builder.createQuery(Session.class);
        criteria.from(Session.class);
        return hibernateSession.createQuery(criteria).getResultList();
    }

}
