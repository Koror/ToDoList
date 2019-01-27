package com.koror.app.api.repository;

import com.koror.app.entity.Session;

import javax.persistence.EntityManager;
import java.util.List;

public interface ISessionRepository extends IRepository<Session>{

    void delete(String id, EntityManager entityManager);

    Session getById(String id, EntityManager entityManager);

    List<Session> getList(EntityManager entityManager);

}
