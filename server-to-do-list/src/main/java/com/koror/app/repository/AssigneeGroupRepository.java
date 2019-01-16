package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;

public class AssigneeGroupRepository extends AbstractRepository<AssigneeGroup> implements IAssigneeGroupRepository {

    @Override
    public void deleteAssigneeByParam(String userId, String groupId) {
        for(AssigneeGroup assigneeGroup : mapEntity.values()){
            if(userId.equals(assigneeGroup.getUserId()) && groupId.equals(assigneeGroup.getGroupId()))
                mapEntity.remove(assigneeGroup.getId());
        }
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId) {
        for (AssigneeGroup assigneeGroup : mapEntity.values()) {
            if (assigneeGroup.getUserId().equals(userId))
                return assigneeGroup;
        }
        return null;
    }

}
