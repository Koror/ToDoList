package com.koror.app.api.repository;

import com.koror.app.entity.User;

import java.util.List;

public interface IUserRepository {

    void addUser(User user);

    void deleteUser(String id);

    List<User> getUserList();

}
