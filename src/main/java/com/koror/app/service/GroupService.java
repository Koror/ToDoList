package com.koror.app.service;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.GroupRepository;

import java.util.List;
import java.util.Map;

public class GroupService implements IGroupRepository {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void addGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        groupRepository.addGroup(group);
    }

    @Override
    public Group updateGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        final Group oldGroup = groupRepository.updateGroup(group);
        if (oldGroup == null) throw new WrongInputException("Wrong input");
        return oldGroup;
    }

    @Override
    public Group deleteGroup(final String id) throws WrongInputException {
        if (id == null || "".equals(id)) throw new WrongInputException("Wrong input");
        Group group = groupRepository.deleteGroup(id);
        if (group == null) throw new WrongInputException("Wrong input");
        return group;
    }

    @Override
    public List<Group> getGroupList() {
        return groupRepository.getGroupList();
    }

    @Override
    public Group getGroup(final Integer index) throws WrongInputException {
        if (index == null) throw new WrongInputException("Wrong input");
        return groupRepository.getGroup(index);
    }

    @Override
    public Group getGroupById(String id) {
        return groupRepository.getGroupById(id);
    }

}
