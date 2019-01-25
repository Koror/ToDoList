package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.util.AppConfig;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void delete(String id) {
        Task entity = hibernateSession.find(Task.class, id);
        hibernateSession.remove(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public Task getById(String id) {
        Task entity = hibernateSession.find(Task.class, id);
        hibernateSession.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Task> getList() {
        CriteriaBuilder builder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        criteria.from(Task.class);
        return hibernateSession.createQuery(criteria).getResultList();
    }

    @Override
    public List<Task> getListTaskByUserId(final String userId) {
        final User user = hibernateSession.find(User.class, userId);
        return user.getTaskList();
    }
}
