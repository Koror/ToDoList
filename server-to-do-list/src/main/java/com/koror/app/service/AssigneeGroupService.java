package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.service.IAssigneeGroupService;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.error.WrongInputException;

import java.util.List;


public class AssigneeGroupService extends AbstractService implements IAssigneeGroupService {

    private final IAssigneeGroupRepository repository;

    public AssigneeGroupService(IAssigneeGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(AssigneeGroup entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    @Override
    public AssigneeGroup getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    @Override
    public List<AssigneeGroup> getList() {
        return repository.getList();
    }

    @Override
    public void update(final AssigneeGroup entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    @Override
    public void deleteAssigneeByParam(String userId, String groupId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        if (groupId == null || groupId.isEmpty()) throw new WrongInputException("Wrong Input");
        for(AssigneeGroup assigneeGroupTemp : repository.getList()){
            if(userId.equals(assigneeGroupTemp.getUser()) && groupId.equals(assigneeGroupTemp.getGroup()))
                repository.delete(assigneeGroupTemp.getId());
        }
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getAssigneeByUserId(userId);
    }

}
