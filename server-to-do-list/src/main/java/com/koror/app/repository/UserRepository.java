package com.koror.app.repository;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.util.Hash;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void add(@NotNull final User user){
        user.setPassword(Hash.createHashString(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void delete(@NotNull final String id) {
        final User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getById(@NotNull final String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getList() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getByLogin(@NotNull final String login) {
        return entityManager.createQuery("FROM User e WHERE e.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

}
