package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.entity.Group;
import com.koror.app.error.WrongInputException;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;

public class GroupServiceTest {

//    @Test(expected = WrongInputException.class)
//    public void testAddGroupNegative() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository,assigneeGroupService);
//        groupService.add(null);
//    }
//
//    @Test
//    public void testAddGroupPositive() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
//        final Group group = new Group("test group");
//        groupService.add(group);
//    }
//
//    @Test(expected = WrongInputException.class)
//    public void testUpdateGroupNegative() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
//        groupService.update(null);
//    }
//
//    @Test
//    public void testUpdateGroupPositive() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
//        final Group group = new Group("test group");
//        groupService.add(group);
//        group.setName("new test group");
//        groupService.update(group);
//    }
//
//    @Test(expected = WrongInputException.class)
//    public void testDeleteGroupNegative() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
//        groupService.delete("");
//    }
//
//    @Test
//    public void testDeleteGroupPositive() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
//        final Group group = new Group();
//        groupService.add(group);
//        groupService.delete(group.getId());
//    }
//
//    @Test(expected = WrongInputException.class)
//    public void testGetGroupNegative() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
//        groupService.getGroupByIndex(null);
//    }
//
//    @Test
//    public void testGetGroupPositive() {
//        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
//        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
//        final GroupRepository groupRepository = new GroupRepository();
//        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
//        final Group group = new Group("test group");
//        groupService.add(group);
//        groupService.getGroupByIndex(0);
//    }

}