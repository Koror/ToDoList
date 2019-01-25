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
        user.setPassword(Hash.createHashString(user.getPassword()));
        hibernateSession.persist(user);
    }

    @Override
    public void delete(String id) {
        User user = hibernateSession.find(User.class, id);
        hibernateSession.remove(user);
    }

    @Override
    public User getById(String id) {
        return hibernateSession.find(User.class, id);
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
        User user = hibernateSession.createQuery("FROM User e WHERE e.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
        return user;
    }

}
