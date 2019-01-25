package com.koror.app.service;

import com.koror.app.entity.AbstractEntity;
import com.koror.app.util.HibernateFactory;

import javax.persistence.EntityManager;

public abstract class AbstractService {

    protected EntityManager hibernateSession = HibernateFactory.sessionFactory.createEntityManager();
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
