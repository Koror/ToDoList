package com.koror.app.api.service;

import com.koror.app.entity.Session;
import com.koror.app.entity.User;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ISessionService {

    void add(@Nullable final Session entity);

    void delete(@Nullable Session session);

    Session getById(@Nullable String id);

    List<Session> getList();

    Session login(User user, String ip);

    void validate(@Nullable String signature);

    void deleteByUserId(@Nullable String userId);

    Session getBySignature(@Nullable String signature);
}
