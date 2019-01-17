package com.koror.app.service;

import com.koror.app.entity.Session;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.SessionRepository;

import java.util.List;

public class SessionService {

    private final SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public void add(Session entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    public Session getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    public List<Session> getList() {
        return repository.getList();
    }

    public void update(final Session entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    public boolean validate(Session session) {
        for (Session sessionTemp : repository.getList()) {
            if (session.equals(sessionTemp))
                return true;
        }
        return false;
    }

}
