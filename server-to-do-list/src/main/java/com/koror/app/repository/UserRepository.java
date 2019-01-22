package com.koror.app.repository;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.util.DatabaseConfig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void delete(String id) {
        hibernateSession.beginTransaction();
        User user = hibernateSession.get(User.class, id);
        hibernateSession.delete(user);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public User getById(String id) {
        hibernateSession.beginTransaction();
        User user = hibernateSession.get(User.class, id);
        hibernateSession.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> getList() {
        String query = "FROM " + User.class.getSimpleName() + "";
        return hibernateSession.createQuery(query).list();
    }

    @Override
    public User getByLogin(String login) {
        hibernateSession.beginTransaction();
        User user = hibernateSession.get(User.class, login);
        hibernateSession.getTransaction().commit();
        return user;
    }

}
