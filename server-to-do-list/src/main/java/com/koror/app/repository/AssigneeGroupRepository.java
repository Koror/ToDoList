package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class AssigneeGroupRepository extends AbstractRepository<AssigneeGroup> implements IAssigneeGroupRepository {

    @Override
    public void delete(@NotNull final String id) {
        AssigneeGroup entity = entityManager.find(AssigneeGroup.class, id);
        entityManager.remove(entity);
    }

    @Override
    public void deleteAssigneeByParam(@NotNull final String userId,@NotNull final String groupId) {
        Query deleteItemsQuery = entityManager.createQuery("DELETE AssigneeGroup ag WHERE ag.user.id =:inpUserId and at.group.id =:inpGroupId");
        deleteItemsQuery.setParameter("inpUserId", userId);
        deleteItemsQuery.setParameter("inpGroupId", groupId);
        deleteItemsQuery.executeUpdate();
    }

    @Override
    public AssigneeGroup getById(@NotNull final String id) {
        return entityManager.find(AssigneeGroup.class, id);
    }

    @Override
    public List<AssigneeGroup> getList() {
        return entityManager.createQuery("FROM AssigneeGroup", AssigneeGroup.class).getResultList();
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(@NotNull final String userId) {
        return entityManager.find(AssigneeGroup.class, userId);
    }

}
