package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.GroupRepository;

import java.io.*;
import java.util.List;

public class GroupService implements IGroupRepository, IDataIO {

    private final GroupRepository groupRepository;

    private final String pathXml = "data/group/data_group.xml";

    private final String pathJson = "data/group/data_group.json";

    private final String pathBin = "data/group/data_group.tmp";

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void addGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        groupRepository.addGroup(group);
    }

    @Override
    public Group updateGroup(final Group group) throws WrongInputException {
        if (group == null) throw new WrongInputException("Wrong input");
        final Group oldGroup = groupRepository.updateGroup(group);
        if (oldGroup == null) throw new WrongInputException("Wrong input");
        return oldGroup;
    }

    @Override
    public Group deleteGroup(final String id) throws WrongInputException {
        if (id == null || "".equals(id)) throw new WrongInputException("Wrong input");
        Group group = groupRepository.deleteGroup(id);
        if (group == null) throw new WrongInputException("Wrong input");
        return group;
    }

    @Override
    public List<Group> getGroupList() {
        return groupRepository.getGroupList();
    }

    @Override
    public Group getGroup(final Integer index) throws WrongInputException {
        if (index == null) throw new WrongInputException("Wrong input");
        return groupRepository.getGroup(index);
    }

    @Override
    public Group getGroupById(String id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public void saveDataSerialization() throws IOException {
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File("data/group/").mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(getGroupList());
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException{
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List<Group> tasks = (List<Group>) ois.readObject();
        for (Group group : tasks) addGroup(group);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File("data/group/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final Group[] listGroup = getGroupList().toArray(new Group[getGroupList().size()]);
        objectMapper.writeValue(new File(pathXml), listGroup);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final Group[] listGroup = objectMapper.readValue(new File(pathXml), Group[].class);
        for (Group group : listGroup) addGroup(group);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File("data/group/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Group[] listGroup = getGroupList().toArray(new Group[getGroupList().size()]);
        objectMapper.writeValue(new File(pathJson), listGroup);
    }

    @Override
    public void loadDataJson() throws IOException{
        final ObjectMapper objectMapper = new ObjectMapper();
        final Group[] listGroup = objectMapper.readValue(new File(pathJson), Group[].class);
        for (Group task : listGroup) addGroup(task);
    }

}
