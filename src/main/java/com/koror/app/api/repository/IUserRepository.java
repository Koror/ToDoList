package com.koror.app.api.repository;

import com.koror.app.entity.User;

import java.util.List;

public interface IUserRepository extends IRepository<User>{

    void loadUser(User user);

    User getByLogin(String login);

}
