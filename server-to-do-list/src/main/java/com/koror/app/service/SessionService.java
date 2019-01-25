package com.koror.app.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.api.service.ISessionService;
import com.koror.app.entity.Session;
import com.koror.app.error.WrongInputException;

import java.util.List;

public class SessionService extends AbstractService implements ISessionService {

    private final ISessionRepository repository;

    public SessionService(ISessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Session entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    @Override
    public void deleteByUserSession(String userId){
        for (Session sessionTemp : repository.getList())
            if (userId.equals(sessionTemp.getUser().getId()))
                repository.delete(sessionTemp.getId());
    }

    @Override
    public Session getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    @Override
    public List<Session> getList() {
        return repository.getList();
    }

    @Override
    public void update(final Session entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
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
