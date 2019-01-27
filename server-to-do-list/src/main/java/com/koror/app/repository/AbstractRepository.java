package com.koror.app.repository;

import com.koror.app.api.repository.IRepository;
import com.koror.app.entity.AbstractEntity;
import com.koror.app.util.HibernateFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    @Override
    public void add(E entity, EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public void update(E entity, EntityManager entityManager) {
        entityManager.merge(entity);
    }

}
