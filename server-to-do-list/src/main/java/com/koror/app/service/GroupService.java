package com.koror.app.service;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.api.service.IGroupService;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;

import java.util.ArrayList;
import java.util.List;

public class GroupService extends AbstractService implements IGroupService {

    private final IGroupRepository repository;

    private final AssigneeGroupService assigneeGroupService;

    public GroupService(IGroupRepository groupRepository, AssigneeGroupService assigneeGroupService) {
        repository = groupRepository;
        this.assigneeGroupService = assigneeGroupService;
    }

    @Override
    public void add(Group entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    @Override
    public Group getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    @Override
    public List<Group> getList() {
        return repository.getList();
    }

    @Override
    public void update(final Group entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    @Override
    public List<Group> getListGroupByUser(User user) {
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        return user.getGroupList();
    }

}
