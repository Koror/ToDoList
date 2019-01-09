package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.repository.AssigneeGroupRepository;

public class AssigneeGroupService implements IAssigneeGroupRepository {

    private final AssigneeGroupRepository assigneeGroupRepository;

    public AssigneeGroupService(AssigneeGroupRepository assigneeGroupRepository){
        this.assigneeGroupRepository = assigneeGroupRepository;
    }

    @Override
    public void addAssignee(AssigneeGroup assigneeGroup) {
        assigneeGroupRepository.addAssignee(assigneeGroup);
    }

    @Override
    public AssigneeGroup getAssigneeById(String id) {
        return assigneeGroupRepository.getAssigneeById(id);
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId) {
        return assigneeGroupRepository.getAssigneeByUserId(userId);
    }

}
