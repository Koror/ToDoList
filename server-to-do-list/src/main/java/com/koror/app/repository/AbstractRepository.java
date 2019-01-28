package com.koror.app.repository;

import com.koror.app.api.repository.IRepository;
import com.koror.app.entity.AbstractEntity;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    @Override
    public void add(@NotNull final E entity, @NotNull final EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public void update(@NotNull final E entity, @NotNull final EntityManager entityManager) {
        entityManager.merge(entity);
    }

}
