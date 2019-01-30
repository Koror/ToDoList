package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeTask;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class AssigneeTaskRepository extends AbstractRepository<AssigneeTask> implements IAssigneeTaskRepository {

    @Override
    public void delete(@NotNull final String id) {
        final AssigneeTask entity = entityManager.find(AssigneeTask.class, id);
        entityManager.remove(entity);
    }

    @Override
    public void deleteAssigneeByParam(@NotNull final String userId, @NotNull final String taskId) {
        final Query deleteItemsQuery = entityManager.createQuery("DELETE AssigneeTask at WHERE at.user.id =:inpUserId and at.task.id =:inpTaskId");
        deleteItemsQuery.setParameter("inpUserId", userId);
        deleteItemsQuery.setParameter("inpTaskId", taskId);
        deleteItemsQuery.executeUpdate();
    }

    @Override
    public AssigneeTask getById(@NotNull final String id) {
        return entityManager.find(AssigneeTask.class, id);
    }

    @Override
    public List<AssigneeTask> getList() {
        return entityManager.createQuery("FROM AssigneeTask", AssigneeTask.class).getResultList();
    }

    @Override
    public AssigneeTask getAssigneeByUserId(@NotNull final String userId) {
        return entityManager.find(AssigneeTask.class, userId);
    }
}
