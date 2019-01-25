package com.koror.app.repository;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.util.AppConfig;
import com.koror.app.util.DatabaseConnection;
import com.koror.app.util.HibernateFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GroupRepositoryTest {

    @Test
    public void testPositiveAddGroup() throws IOException {
//        AppConfig.init();
//        DatabaseConnection.setConnection();
//        HibernateFactory.buildFactory();
//        GroupRepository groupRepository = new GroupRepository();
//        Group group = new Group();
//        group.setName("TestGroup");
//        groupRepository.add(group);
//        DatabaseConnection.closeConnection();
    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testNegativeAddGroup() {
//        final GroupRepository groupRepository = new GroupRepository();
//        groupRepository.getGroupByIndex(0);
//    }
//
//    @Test
//    public void testPositiveUpdateGroup() {
//        final GroupRepository groupRepository = new GroupRepository();
//        Group group = new Group("Test group");
//        final String group = group.getId();
//        groupRepository.add(group);
//        group = new Group("New test group");
//        groupRepository.update(group);
//        assertNotEquals(group, groupRepository.getGroupByIndex(0));
//    }
//
//    @Test
//    public void testNegativeUpdateGroup() {
//        final GroupRepository groupRepository = new GroupRepository();
//        final Group group = new Group("Test group");
//        final String group = group.getId();
//        groupRepository.add(group);
//        assertEquals(group, groupRepository.getGroupByIndex(0).getId());
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testPositiveDeleteGroup() {
//        final GroupRepository groupRepository = new GroupRepository();
//        final Group group = new Group("Test group");
//        groupRepository.add(group);
//        groupRepository.delete(group.getId());
//        assertNull(groupRepository.getGroupByIndex(0));
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testNegativeDeleteGroup() {
//        final GroupRepository groupRepository = new GroupRepository();
//        Group group = new Group("Test group");
//        groupRepository.add(group);
//        groupRepository.delete(group.getId());
//        group = groupRepository.getGroupByIndex(0);
//        assertNull(group.getId());
//        groupRepository.delete("");
//    }
//
//    @Test
//    public void testPositiveGetGroupList() {
//        final GroupRepository groupRepository = new GroupRepository();
//        final Group group = new Group("Test group");
//        groupRepository.add(group);
//        assertNotNull(groupRepository.getList());
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testNegativeGetGroupList() {
//        final GroupRepository groupRepository = new GroupRepository();
//        groupRepository.getGroupByIndex(0);
//    }
//
//    @Test
//    public void testPositiveGetGroup() {
//        final GroupRepository groupRepository = new GroupRepository();
//        final Group group = new Group("Test group");
//        groupRepository.add(group);
//        assertNotNull(groupRepository.getGroupByIndex(0));
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testNegativeGetGroup() {
//        final GroupRepository groupRepository = new GroupRepository();
//        groupRepository.getGroupByIndex(0);
//    }

}