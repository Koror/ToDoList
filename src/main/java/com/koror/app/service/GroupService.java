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

    public void addGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        groupRepository.addGroup(group);
    }

    public Group updateGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        final Group oldGroup = groupRepository.updateGroup(group);
        if (oldGroup == null) throw new WrongInputException("Wrong input");
        return oldGroup;
    }

    public Group deleteGroup(final String id) throws WrongInputException {
        if (id == null) throw new WrongInputException("Wrong input");
        Group group = groupRepository.deleteGroup(id);
        if (group == null) throw new WrongInputException("Wrong input");
        return group;
    }

    public Map<String, Group> getGroupMap() {
        return groupRepository.getGroupMap();
    }

    public List<Group> getGroupList() {
        return groupRepository.getGroupList();
    }

    public Group getGroup(final Integer index) throws WrongInputException {
        if (index == null) throw new WrongInputException("Wrong input");
        return groupRepository.getGroup(index);
    }

}
