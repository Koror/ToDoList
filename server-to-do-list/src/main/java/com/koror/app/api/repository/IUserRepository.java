package com.koror.app.api.repository;

import com.koror.app.entity.User;

import java.util.List;

public interface IUserRepository extends IRepository<User>{

    User getByLogin(String login);

    User login(String login, String password);

}
