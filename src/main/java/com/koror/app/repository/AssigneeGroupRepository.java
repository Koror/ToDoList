package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;

import java.util.HashMap;
import java.util.Map;

public class AssigneeGroupRepository implements IAssigneeGroupRepository {

    private final Map<String, AssigneeGroup> assigneeGroupMap = new HashMap<>();

    @Override
    public void addAssignee(AssigneeGroup assigneeTask) {
        assigneeGroupMap.put(assigneeTask.getId(), assigneeTask);
    }

    @Override
    public AssigneeGroup getAssigneeById(String id) {
        return assigneeGroupMap.get(id);
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId) {
        for (AssigneeGroup assigneeGroup : assigneeGroupMap.values()) {
            if (assigneeGroup.getUserId().equals(userId))
                return assigneeGroup;
        }
        return null;
    }

}
