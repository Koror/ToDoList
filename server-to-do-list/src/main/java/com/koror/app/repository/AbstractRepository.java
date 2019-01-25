package com.koror.app.repository;

import com.koror.app.api.repository.IRepository;
import com.koror.app.entity.AbstractEntity;
import com.koror.app.util.HibernateFactory;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    protected EntityManager hibernateSession = HibernateFactory.sessionFactory.createEntityManager();

    @Override
    public void add(E entity) {
        hibernateSession.getTransaction().begin();
        hibernateSession.persist(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public void update(E entity) {
        hibernateSession.getTransaction().begin();
        hibernateSession.refresh(entity);
        hibernateSession.getTransaction().commit();
    }

}
