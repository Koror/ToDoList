package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssigneeGroupRepository implements IAssigneeGroupRepository {

    private final Map<String, AssigneeGroup> assigneeGroupMap = new HashMap<>();

    @Override
    public void addAssignee(AssigneeGroup assigneeTask) {
        assigneeGroupMap.put(assigneeTask.getId(), assigneeTask);
    }

    @Override
    public void deleteAssignee(String id) {
        assigneeGroupMap.remove(id);
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

    @Override
    public List<AssigneeGroup> getAssigneeGroupList() {
        return new ArrayList<>(assigneeGroupMap.values());
    }

}
