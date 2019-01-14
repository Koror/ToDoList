package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.GroupRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupService extends AbstractService<GroupRepository, Group> implements IGroupRepository, IDataIO {

    private final AssigneeGroupService assigneeGroupService;

    private final String pathXml = "data/group/data_group.xml";

    private final String pathJson = "data/group/data_group.json";

    private final String pathBin = "data/group/data_group.tmp";

    private final String pathPackage = "data/group/";

    public GroupService(GroupRepository groupRepository, AssigneeGroupService assigneeGroupService) {
        repository = groupRepository;
        this.assigneeGroupService = assigneeGroupService;
    }

    @Override
    public Group getGroupByIndex(final Integer index) throws WrongInputException {
        if (index == null) throw new WrongInputException("Wrong input");
        return repository.getGroupByIndex(index);
    }

    public List<Group> getListGroupByUser(User user) {
        if(user.getAccess()== Access.ADMIN) return getList();

        final List<Group> groupList = new ArrayList<>();
        for (final AssigneeGroup assigneeGroups : assigneeGroupService.getList()) {
            if (user.getId().equals(assigneeGroups.getUserId()))
                groupList.add(getById(assigneeGroups.getGroupId()));
        }
        return groupList;
    }

    @Override
    public void saveDataSerialization() throws IOException {
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(getList());
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException {
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List<Group> tasks = (List<Group>) ois.readObject();
        for (Group group : tasks) add(group);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final Group[] listGroup = getList().toArray(new Group[getList().size()]);
        objectMapper.writeValue(new File(pathXml), listGroup);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final Group[] listGroup = objectMapper.readValue(new File(pathXml), Group[].class);
        for (Group group : listGroup) add(group);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Group[] listGroup = getList().toArray(new Group[getList().size()]);
        objectMapper.writeValue(new File(pathJson), listGroup);
    }

    @Override
    public void loadDataJson() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Group[] listGroup = objectMapper.readValue(new File(pathJson), Group[].class);
        for (Group task : listGroup) add(task);
    }

}
