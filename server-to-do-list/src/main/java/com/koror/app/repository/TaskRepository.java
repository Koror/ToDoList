package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;
import com.koror.app.util.DatabaseConfig;

import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void delete(String id) {
        hibernateSession.beginTransaction();
        Task entity = hibernateSession.get(Task.class, id);
        hibernateSession.delete(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public Task getById(String id) {
        hibernateSession.beginTransaction();
        Task entity = hibernateSession.get(Task.class, id);
        hibernateSession.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Task> getList() {
        String query = "select p from " + DatabaseConfig.PREFIXDB + Task.class.getSimpleName() + " p";
        return (List<Task>) hibernateSession.createQuery(query).list();
    }

}
