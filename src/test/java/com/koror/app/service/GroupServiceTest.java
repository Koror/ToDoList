package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.entity.Group;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeGroupRepository;
import com.koror.app.repository.GroupRepository;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
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
    public void saveDataSerialization() throws IOException {
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        final Group group = new Group("test group");
        groupService.addGroup(group);

        final String pathBin = "test_data/group/data_group.tmp";
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File("test_data/group/").mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(groupService.getGroupList());
        File checkFile = new File("test_data/group/data_group.tmp");
        assertTrue(checkFile.exists());
    }

    @Test
    public void loadDataSerialization() throws IOException, ClassNotFoundException{
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);

        final String pathBin = "test_data/group/data_group.tmp";
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List<Group> tasks = (List<Group>) ois.readObject();
        for (Group group : tasks) groupService.addGroup(group);
        assertNotNull(groupService.getGroupList().get(0).getId());
    }

    @Test
    public void saveDataXml() throws IOException{
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        final Group group = new Group("test group");
        groupService.addGroup(group);

        final String pathXml = "test_data/group/data_group.xml";
        final File f = new File(pathXml);
        new File("test_data/group/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final Group[] listGroup = groupService.getGroupList().toArray(new Group[groupService.getGroupList().size()]);
        objectMapper.writeValue(new File(pathXml), listGroup);
        File checkFile = new File("test_data/group/data_group.xml");
        assertTrue(checkFile.exists());
    }

    @Test
    public void loadDataXml() throws IOException{
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);

        final String pathXml = "test_data/group/data_group.xml";
        final ObjectMapper objectMapper = new XmlMapper();
        final Group[] listGroup = objectMapper.readValue(new File(pathXml), Group[].class);
        for (Group group : listGroup) groupService.addGroup(group);
    }

    @Test
    public void saveDataJson() throws IOException{
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);
        final Group group = new Group("test group");
        groupService.addGroup(group);

        final String pathJson = "data/group/data_group.json";
        final File f = new File(pathJson);
        new File("data/group/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Group[] listGroup = groupService.getGroupList().toArray(new Group[groupService.getGroupList().size()]);
        objectMapper.writeValue(new File(pathJson), listGroup);
    }

    @Test
    public void loadDataJson() throws IOException{
        final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();
        final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        final GroupRepository groupRepository = new GroupRepository();
        final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);

        final String pathJson = "data/group/data_group.json";
        final ObjectMapper objectMapper = new ObjectMapper();
        final Group[] listGroup = objectMapper.readValue(new File(pathJson), Group[].class);
        for (Group task : listGroup) groupService.addGroup(task);
    }
}