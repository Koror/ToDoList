package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.service.IAssigneeGroupService;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class AssigneeGroupService extends AbstractService<IAssigneeGroupRepository, AssigneeGroup> implements IAssigneeGroupService {

    public AssigneeGroupService(IAssigneeGroupRepository repository, EntityManagerFactory entityManagerFactory) {
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
    public AssigneeGroup getById(@Nullable final String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final AssigneeGroup assigneeGroup = repository.getById(id, entityManager);
        entityManager.close();
        return assigneeGroup;
    }

    @Override
    public List<AssigneeGroup> getList() {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final List<AssigneeGroup> list = repository.getList(entityManager);
        entityManager.close();
        return list;
    }

    @Override
    public void deleteAssigneeByParam(@Nullable final String userId,@Nullable final String groupId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        if (groupId == null || groupId.isEmpty()) throw new WrongInputException("Wrong Input");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for(final AssigneeGroup assigneeGroupTemp : repository.getList(entityManager)){
            if(userId.equals(assigneeGroupTemp.getUser()) && groupId.equals(assigneeGroupTemp.getGroup()))
                repository.delete(assigneeGroupTemp.getId(), entityManager);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final AssigneeGroup assigneeGroup = repository.getAssigneeByUserId(userId, entityManager);
        entityManager.close();
        return assigneeGroup;
    }

}