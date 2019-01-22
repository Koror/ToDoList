package com.koror.app.repository;

import com.koror.app.api.repository.IRepository;
import com.koror.app.entity.AbstractEntity;
import com.koror.app.util.HibernateFactory;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    protected org.hibernate.Session hibernateSession = HibernateFactory.sessionFactory.openSession();

    @Override
    public void add(E entity) {
        hibernateSession.beginTransaction();
        hibernateSession.persist(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public void update(E entity) {
        hibernateSession.beginTransaction();
        hibernateSession.update(entity);
        hibernateSession.getTransaction().commit();
    }

}
