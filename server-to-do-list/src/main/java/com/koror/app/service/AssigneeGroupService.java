package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeGroupRepository;

import java.util.List;


public class AssigneeGroupService implements IAssigneeGroupRepository {

    private final AssigneeGroupRepository repository;

    public AssigneeGroupService(AssigneeGroupRepository repository) {
        this.repository = repository;
    }

    public void add(AssigneeGroup entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    public AssigneeGroup getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    public List<AssigneeGroup> getList() {
        return repository.getList();
    }

    public void update(final AssigneeGroup entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    @Override
    public void deleteAssigneeByParam(String userId, String groupId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        if (groupId == null || groupId.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.deleteAssigneeByParam(userId, groupId);
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getAssigneeByUserId(userId);
    }

}
