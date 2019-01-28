package com.koror.app.repository;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.entity.Session;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.List;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    public void delete(@NotNull final String id, @NotNull final EntityManager entityManager) {
        final Session entity = entityManager.find(Session.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Session getById(@NotNull final String id, @NotNull final EntityManager entityManager) {
        return entityManager.find(Session.class, id);
    }

    @Override
    public List<Session> getList(@NotNull final EntityManager entityManager) {
        return entityManager.createQuery("FROM Session", Session.class).getResultList();
    }

}
