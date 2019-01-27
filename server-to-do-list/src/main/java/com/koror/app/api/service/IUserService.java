package com.koror.app.api.service;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public interface IUserService{

    User getByLogin(String login);

    void delete(String id);

    User getById(String id);

    List<User> getList();

    User login(String login, String password);

    void linkToTask(User user, Task task);

}
