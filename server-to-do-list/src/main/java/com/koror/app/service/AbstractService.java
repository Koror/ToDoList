package com.koror.app.service;

import com.koror.app.entity.AbstractEntity;

public abstract class AbstractService< E extends AbstractEntity> {

//    protected R repository;
//
//    public AbstractService(){
//
//    }
//
//    public void add(E entity) {
//        if(entity==null) throw new WrongInputException("Wrong Input");
//        repository.add(entity);
//    }
//
//    public void delete(String id) {
//        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
//        repository.delete(id);
//    }
//
//    public E getById(String id) {
//        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
//        return (E)repository.getById(id);
//    }
//
//    public List<E> getList() {
//        return repository.getList();
//    }
//
//    public E update(final E entity) {
//        if (entity == null) throw new WrongInputException("Wrong input");
//        return (E)repository.update(entity);
//    }
}
