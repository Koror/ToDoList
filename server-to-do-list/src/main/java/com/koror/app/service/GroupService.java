package com.koror.app.service;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

public class GroupService implements IGroupRepository {

    private final GroupRepository repository;

    private final AssigneeGroupService assigneeGroupService;

    public void add(Group entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    public Group getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    public List<Group> getList() {
        return repository.getList();
    }

    public void update(final Group entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    public GroupService(GroupRepository groupRepository, AssigneeGroupService assigneeGroupService) {
        repository = groupRepository;
        this.assigneeGroupService = assigneeGroupService;
    }

    @Override
    public Group getGroupByIndex(final Integer index) throws WrongInputException {
        if (index == null) throw new WrongInputException("Wrong input");
        return repository.getGroupByIndex(index);
    }

    public List<Group> getListGroupByUser(User user) {
        if (user.getAccess() == Access.ADMIN) return getList();

        final List<Group> groupList = new ArrayList<>();
        for (final AssigneeGroup assigneeGroups : assigneeGroupService.getList()) {
            if (user.getId().equals(assigneeGroups.getUserId()))
                groupList.add(getById(assigneeGroups.getGroupId()));
        }
        return groupList;
    }

}
