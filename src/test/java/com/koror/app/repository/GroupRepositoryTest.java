package com.koror.app.repository;

import com.koror.app.entity.Group;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupRepositoryTest {

    @Test
    public void testPositiveAddGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        groupRepository.addGroup(group);
        assertNotNull(groupRepository.getGroupList().get(0).getId());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeAddGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        groupRepository.getGroupList().get(0);
    }

    @Test
    public void testPositiveUpdateGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        final String groupId = group.getId();
        groupRepository.addGroup(group);
        group = new Group("New test group");
        groupRepository.updateGroup(group);
        assertNotEquals(groupId,groupRepository.getGroupList().get(0));
    }

    @Test
    public void testNegativeUpdateGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        final String groupId = group.getId();
        groupRepository.addGroup(group);
        assertEquals(groupId, groupRepository.getGroupList().get(0).getId());
    }

    @Test
    public void testPositiveDeleteGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        groupRepository.addGroup(group);
        groupRepository.getGroupMap().remove(group.getId());
        assertNull(groupRepository.getGroupMap().get(0));
    }

    @Test(expected = NullPointerException.class)
    public void testNegativeDeleteGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        groupRepository.addGroup(group);
        groupRepository.getGroupMap().remove(group.getId());
        group = groupRepository.getGroupMap().get(0);
        group.getId();
    }

    @Test
    public void testPositiveGetGroupMap() {
        final GroupRepository groupRepository = new GroupRepository();
        assertNotNull(groupRepository.getGroupMap());
    }

    @Test(expected = NullPointerException.class)
    public void testNegativeGetGroupMap() {
        final GroupRepository groupRepository = null;
        groupRepository.getGroupMap();
    }

    @Test
    public void testPositiveGetGroupList() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        groupRepository.addGroup(group);
        assertNotNull(groupRepository.getGroupList());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeGetGroupList() {
        final GroupRepository groupRepository = new GroupRepository();
        groupRepository.getGroupList().get(0);
    }

    @Test
    public void testPositiveGetGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        groupRepository.addGroup(group);
        assertNotNull(groupRepository.getGroupList().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeGetGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        groupRepository.getGroupList().get(0);
    }

}