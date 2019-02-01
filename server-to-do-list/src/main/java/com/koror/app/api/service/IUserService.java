package com.koror.app.api.service;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import java.util.List;

public interface IUserService{

    void add(@Nullable final User entity);

    User getByLogin(@Nullable String login);

    void delete(@Nullable User user);

    User getById(@Nullable String id);

    List<User> getList();

    User login(@Nullable String login, @Nullable String password);

    void linkToTask(@Nullable User user, @Nullable Task task);

}
