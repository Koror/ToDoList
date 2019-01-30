package com.koror.app.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.api.service.ISessionService;
import com.koror.app.entity.Session;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SessionService extends AbstractService<ISessionRepository, Session> implements ISessionService {

    @Override
    public void delete(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        repository.delete(id);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void deleteByUserSession(@Nullable String userId){
        if (userId == null) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        for (Session sessionTemp : repository.getList())
            if (userId.equals(sessionTemp.getUser().getId()))
                repository.delete(sessionTemp.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public Session getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        Session session = repository.getById(id);
        entityManager.clear();
        return session;
    }

    @Override
    public List<Session> getList() {
        List<Session> list = repository.getList();
        entityManager.clear();
        return list;
    }

    @Override
    public boolean validate(@Nullable Session session) {
        if (session == null) throw new WrongInputException("Wrong Input");
        for (Session sessionTemp : getList()) {
            if (session.equals(sessionTemp))
                return true;
        }
        return false;
    }

}
