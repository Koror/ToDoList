package com.koror.app.api.service;

import com.koror.app.entity.User;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IUserService {

    void add(@Nullable final User entity);

    User getByLogin(@Nullable String login);

    void delete(@Nullable User user);

    void update(@Nullable final User entity);

    User getById(@Nullable String id);

    List<User> getList();

    User login(@Nullable String login, @Nullable String password);

}
