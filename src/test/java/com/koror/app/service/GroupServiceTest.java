package com.koror.app.service;

import com.koror.app.entity.Group;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.GroupRepository;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GroupServiceTest {

    @Test(expected = WrongInputException.class)
    public void addGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository);
        groupService.addGroup(null);
    }

    @Test(expected = WrongInputException.class)
    public void updateGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository);
        groupService.updateGroup(null);
    }

    @Test
    public void deleteGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository);
        assertNull(groupService.deleteGroup("undefined group id"));
    }

    @Test(expected = WrongInputException.class)
    public void getGroup() {
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository);
        groupService.getGroup(null);
    }
}