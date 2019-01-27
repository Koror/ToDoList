package com.koror.app.api.repository;

import com.koror.app.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public interface IUserRepository extends IRepository<User>{

    User getByLogin(String login, EntityManager entityManager);

    void delete(String id, EntityManager entityManager);

    User getById(String id, EntityManager entityManager);

    List<User> getList(EntityManager entityManager);

}
