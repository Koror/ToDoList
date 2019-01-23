package com.koror.app.repository;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.util.Hash;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void add(User user){
        hibernateSession.beginTransaction();
        user.setPassword(Hash.createHashString(user.getPassword()));
        hibernateSession.persist(user);
        hibernateSession.getTransaction().commit();

    }
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
        CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return hibernateSession.createQuery(criteria).getResultList();
    }

    @Override
    public User getByLogin(String login) {
        hibernateSession.beginTransaction();
        User user = hibernateSession.get(User.class, login);
        hibernateSession.getTransaction().commit();
        return user;
    }

}
