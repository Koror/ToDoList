package com.koror.app.service;

import com.koror.app.entity.AbstractEntity;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AbstractRepository;

import java.util.List;

public abstract class AbstractService<R extends AbstractRepository, E extends AbstractEntity> {

    protected R repository;

    public AbstractService(){

    }

    public void add(E entity) {
        if(entity==null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    public void delete(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    public E getById(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return (E)repository.getById(id);
    }

    public List<E> getList() {
        return repository.getList();
    }

}
