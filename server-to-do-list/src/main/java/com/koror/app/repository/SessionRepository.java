package com.koror.app.repository;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.entity.Session;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    public void delete(String id, EntityManager entityManager) {
        Session entity = entityManager.find(Session.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Session getById(String id, EntityManager entityManager) {
        return entityManager.find(Session.class, id);
    }

    @Override
    public List<Session> getList(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Session> criteria = builder.createQuery(Session.class);
        criteria.from(Session.class);
        return entityManager.createQuery(criteria).getResultList();
    }

}
