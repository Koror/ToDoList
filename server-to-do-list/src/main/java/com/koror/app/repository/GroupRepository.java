package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import java.util.List;

public class GroupRepository extends AbstractRepository<Group> implements IGroupRepository {

    @Override
    public void delete(@NotNull final String id, @NotNull final EntityManager entityManager) {
        final Group entity = entityManager.find(Group.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Group getById(@NotNull final String id, @NotNull final EntityManager entityManager) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getList(@NotNull final EntityManager entityManager) {
        return entityManager.createQuery("FROM Group", Group.class).getResultList();
    }

    @Override
    public List<Group> getListGroupByUserId(@NotNull final String userId, @NotNull final EntityManager entityManager) {
        final List<Group> groupList = entityManager.createQuery("FROM Group e WHERE e.user.id = :userId", Group.class)
                .setParameter("userId", userId)
                .getResultList();
        return groupList;
    }
}
