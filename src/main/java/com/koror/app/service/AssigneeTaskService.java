package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.repository.AssigneeTaskRepository;

import java.util.Map;

public class AssigneeTaskService implements IAssigneeTaskRepository {

    private final AssigneeTaskRepository assigneeTaskRepository;

    public AssigneeTaskService(AssigneeTaskRepository assigneeTaskRepository) {
        this.assigneeTaskRepository = assigneeTaskRepository;
    }

    @Override
    public void addAssignee(AssigneeTask assigneeTask) {
        assigneeTaskRepository.addAssignee(assigneeTask);
    }

    @Override
    public AssigneeTask getAssigneeById(String id) {
        return assigneeTaskRepository.getAssigneeById(id);
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        return assigneeTaskRepository.getAssigneeByUserId(userId);
    }

}
