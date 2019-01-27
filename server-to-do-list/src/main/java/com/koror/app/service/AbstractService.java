package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.repository.IRepository;
import com.koror.app.entity.AbstractEntity;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AbstractRepository;
import com.koror.app.util.HibernateFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class AbstractService<R extends IRepository,E extends AbstractEntity> {

    protected EntityManagerFactory entityManagerFactory;

    protected R repository;

    public AbstractService(){

    }

    public void add(E entity) {
        if(entity==null) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        repository.add(entity, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(final E entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        repository.update(entity, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
