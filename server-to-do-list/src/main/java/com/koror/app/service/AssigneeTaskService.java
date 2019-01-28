package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.service.IAssigneeTaskService;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeTaskRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AssigneeTaskService extends AbstractService<IAssigneeTaskRepository, AssigneeTask> implements IAssigneeTaskService {

    public AssigneeTaskService(@NotNull final IAssigneeTaskRepository repository,@NotNull final EntityManagerFactory entityManagerFactory) {
        this.repository = repository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void delete(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        repository.delete(id, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public AssigneeTask getById(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        AssigneeTask assigneeTask = repository.getById(id, entityManager);
        entityManager.close();
        return assigneeTask;
    }

    @Override
    public List<AssigneeTask> getList() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final List<AssigneeTask> list = repository.getList(entityManager);
        entityManager.close();
        return list;
    }

    @Override
    public void deleteAssigneeByParam(@Nullable final String userId,@Nullable final String taskId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        if (taskId == null || taskId.isEmpty()) throw new WrongInputException("Wrong Input");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for(final AssigneeTask assigneeTaskTemp : repository.getList(entityManager)){
            if(userId.equals(assigneeTaskTemp.getUser().getId()) && taskId.equals(assigneeTaskTemp.getTask().getId()))
                repository.delete(assigneeTaskTemp.getId(), entityManager);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public AssigneeTask getAssigneeByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final AssigneeTask assigneeTask = repository.getAssigneeByUserId(userId, entityManager);
        entityManager.close();
        return assigneeTask;
    }

}
