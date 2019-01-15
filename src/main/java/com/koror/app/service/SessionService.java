package com.koror.app.service;

import com.koror.app.entity.Session;
import com.koror.app.repository.SessionRepository;

public class SessionService extends AbstractService<SessionRepository, Session> {

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

}
