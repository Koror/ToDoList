package com.koror.app.api.repository;

import com.koror.app.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public interface IUserRepository extends IRepository<User>{

    User getByLogin(String login);

    void delete(String id);

    User getById(String id);

    List<User> getList();

}
