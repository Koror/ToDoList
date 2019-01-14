package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;

public class GroupRepository extends AbstractRepository<Group> implements IGroupRepository {

    @Override
    public Group updateGroup(final Group group) {
        return mapEntity.put(group.getId(), group);
    }

    @Override
    public Group getGroupByIndex(final Integer index) {
        return getList().get(index);
    }

    public Group getGroupById(final String id) {
        return mapEntity.get(id);
    }

}
