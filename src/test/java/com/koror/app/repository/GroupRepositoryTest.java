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
        assertNotNull(groupRepository.getGroup(0).getId());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeAddGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        groupRepository.getGroup(0);
    }

    @Test
    public void testPositiveUpdateGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        final String groupId = group.getId();
        groupRepository.addGroup(group);
        group = new Group("New test group");
        groupRepository.updateGroup(group);
        assertNotEquals(groupId, groupRepository.getGroup(0));
    }

    @Test
    public void testNegativeUpdateGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        final String groupId = group.getId();
        groupRepository.addGroup(group);
        assertEquals(groupId, groupRepository.getGroup(0).getId());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testPositiveDeleteGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        groupRepository.addGroup(group);
        groupRepository.deleteGroup(group.getId());
        assertNull(groupRepository.getGroup(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeDeleteGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        Group group = new Group("Test group");
        groupRepository.addGroup(group);
        groupRepository.deleteGroup(group.getId());
        group = groupRepository.getGroup(0);
        assertNull(group.getId());
        groupRepository.deleteGroup("");
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
        groupRepository.getGroup(0);
    }

    @Test
    public void testPositiveGetGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final Group group = new Group("Test group");
        groupRepository.addGroup(group);
        assertNotNull(groupRepository.getGroup(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNegativeGetGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        groupRepository.getGroup(0);
    }

}