package com.koror.app.repository;

import com.koror.app.entity.Group;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupRepositoryTest {

    @Test
    public void addGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        groupRepository.addGroup(group);
        assertNotNull(groupRepository.getGroupList().get(0).getId());
    }

    @Test
    public void updateGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        final String groupId = group.getId();
        groupRepository.addGroup(group);
        group = new Group("New test group");
        groupRepository.updateGroup(group);
        assertFalse(groupId.equals(groupRepository.getGroupList().get(1)));
    }

    @Test(expected = NullPointerException.class)
    public void deleteGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        groupRepository.addGroup(group);
        groupRepository.getGroupMap().remove(group.getId());
        group = groupRepository.getGroupMap().get(0);
        group.getId();
    }

    @Test
    public void getGroupMap() {
        final GroupRepository groupRepository = new GroupRepository();
        assertNotNull(groupRepository.getGroupMap());
    }

    @Test
    public void getGroupList() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        groupRepository.addGroup(group);
        assertNotNull(groupRepository.getGroupList());
    }

    @Test
    public void getGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        groupRepository.addGroup(group);
        assertNotNull(groupRepository.getGroupList().get(0));
    }
}