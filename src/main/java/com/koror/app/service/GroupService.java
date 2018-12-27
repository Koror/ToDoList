package com.koror.app.service;

import com.koror.app.entity.Group;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.GroupRepository;

import java.util.List;
import java.util.Map;

public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void addGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        groupRepository.addGroup(group);
    }

    public void updateGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        groupRepository.updateGroup(group);
    }

    public void deleteGroup(final String id) throws WrongInputException {
        try {
            groupRepository.deleteGroup(id);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new WrongInputException("Wrong input");
        }
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
