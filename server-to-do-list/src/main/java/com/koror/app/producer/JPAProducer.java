package com.koror.app.producer;

import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JPAProducer {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @TransactionScoped
    public EntityManager create() {
        return entityManagerFactory.createEntityManager();
    }

    public void close(@Disposes @Any EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
