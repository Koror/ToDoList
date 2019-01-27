package com.koror.app.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.api.service.ISessionService;
import com.koror.app.entity.Session;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.SessionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class SessionService extends AbstractService<ISessionRepository, Session> implements ISessionService {

    public SessionService(ISessionRepository repository, EntityManagerFactory entityManagerFactory) {
        this.repository = repository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        repository.delete(id, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteByUserSession(String userId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Session sessionTemp : repository.getList(entityManager))
            if (userId.equals(sessionTemp.getUser().getId()))
                repository.delete(sessionTemp.getId(), entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Session getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Session session = repository.getById(id, entityManager);
        entityManager.close();
        return session;
    }

    @Override
    public List<Session> getList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Session> list = repository.getList(entityManager);
        entityManager.close();
        return list;
    }

    @Override
    public boolean validate(Session session) {
        for (Session sessionTemp : getList()) {
            if (session.equals(sessionTemp))
                return true;
        }
        return false;
    }

}
