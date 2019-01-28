package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void delete(@NotNull final String id, @NotNull final EntityManager entityManager) {
        final Task entity = entityManager.find(Task.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Task getById(@NotNull final String id, @NotNull final EntityManager entityManager) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getList(@NotNull final EntityManager entityManager) {
        return entityManager.createQuery("FROM Task", Task.class).getResultList();
    }

    @Override
    public List<Task> getListTaskByUserId(@NotNull final String userId, @NotNull final EntityManager entityManager) {
        final List<Task> taskList = entityManager.createQuery("FROM Task e WHERE e.user.id = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        return taskList;
    }
}
