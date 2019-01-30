package com.koror.app.api.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.entity.Session;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import java.util.List;

public interface ISessionService {

    void add(@Nullable final Session entity);

    void delete(@Nullable String id);

    Session getById(@Nullable String id);

    List<Session> getList();

    boolean validate(@Nullable Session session);

    void deleteByUserSession(@Nullable String userId);

}
