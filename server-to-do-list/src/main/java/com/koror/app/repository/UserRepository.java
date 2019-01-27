package com.koror.app.repository;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.util.Hash;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void add(User user, EntityManager entityManager){
        user.setPassword(Hash.createHashString(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void delete(String id, EntityManager entityManager) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getById(String id, EntityManager entityManager) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getList(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public User getByLogin(String login, EntityManager entityManager) {
        User user = entityManager.createQuery("FROM User e WHERE e.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
        return user;
    }

}
