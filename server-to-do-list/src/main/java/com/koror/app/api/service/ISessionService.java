package com.koror.app.api.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.entity.Session;

public interface ISessionService extends ISessionRepository {

    boolean validate(Session session);

    void deleteByUserSession(String userId);

}
