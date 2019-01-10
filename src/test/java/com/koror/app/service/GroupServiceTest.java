package com.koror.app.service;

import com.koror.app.entity.Group;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeGroupRepository;
import com.koror.app.repository.GroupRepository;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GroupServiceTest {

    @Test(expected = WrongInputException.class)
    public void testAddGroupNegative() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository,assigneeGroupService);
        groupService.addGroup(null);
    }

    @Test
    public void testAddGroupPositive() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        final Group group = new Group("test group");
        groupService.addGroup(group);
    }

    @Test(expected = WrongInputException.class)
    public void testUpdateGroupNegative() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        groupService.updateGroup(null);
    }

    @Test
    public void testUpdateGroupPositive() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        final Group group = new Group("test group");
        groupService.addGroup(group);
        group.setName("new test group");
        assertNotNull(groupService.updateGroup(group));
    }

    @Test(expected = WrongInputException.class)
    public void testDeleteGroupNegative() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        groupService.deleteGroup("undefined group id");
    }

    @Test
    public void testDeleteGroupPositive() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        final Group group = new Group();
        groupService.addGroup(group);
        groupService.deleteGroup(group.getId());
    }

    @Test(expected = WrongInputException.class)
    public void testGetGroupNegative() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        groupService.getGroup(null);
    }

    @Test
    public void testGetGroupPositive() {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        final Group group = new Group("test group");
        groupService.addGroup(group);
        groupService.getGroup(0);
    }

    @Test
    public void saveDataSerialization() {
    }

    @Test
    public void loadDataSerialization() {
    }

    @Test
    public void saveDataXml() {
    }

    @Test
    public void loadDataXml() {
    }

    @Test
    public void saveDataJson() {
    }

    @Test
    public void loadDataJson() {
    }
}