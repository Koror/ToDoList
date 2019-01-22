package com.koror.app.api.repository;

import com.koror.app.entity.Session;

import java.util.List;

public interface ISessionRepository extends IRepository<Session>{

    void delete(String id);

    Session getById(String id);

    List<Session> getList();

}
