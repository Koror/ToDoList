package com.koror.app.service;

import com.koror.app.api.repository.IRepository;
import com.koror.app.entity.AbstractEntity;
import com.koror.app.error.WrongInputException;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class AbstractService<R extends IRepository,E extends AbstractEntity> {

    @Inject
    protected EntityManager entityManager;

    @Inject
    protected R repository;

    public AbstractService(){

    }

    public void add(@Nullable final E entity) {
        if(entity==null) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        repository.add(entity);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    public void update(@Nullable final E entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        entityManager.getTransaction().begin();
        repository.update(entity);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}
