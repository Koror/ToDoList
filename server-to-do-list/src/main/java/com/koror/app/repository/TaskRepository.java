package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void delete(String id, EntityManager entityManager) {
        Task entity = entityManager.find(Task.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Task getById(String id, EntityManager entityManager) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getList(EntityManager entityManager) {
        return entityManager.createQuery("FROM Task", Task.class).getResultList();
    }

    @Override
    public List<Task> getListTaskByUserId(final String userId, EntityManager entityManager) {
        List<Task> taskList = entityManager.createQuery("FROM Task e WHERE e.user.id = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        return taskList;
    }
}
