package com.koror.app.api.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.entity.Session;

import javax.persistence.EntityManager;
import java.util.List;

public interface ISessionService {

    void delete(String id);

    Session getById(String id);

    List<Session> getList();

    boolean validate(Session session);

    void deleteByUserSession(String userId);

}
