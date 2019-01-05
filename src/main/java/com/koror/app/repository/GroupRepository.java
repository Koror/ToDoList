package com.koror.app.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepository implements IGroupRepository {

    private final Map<String, Group> groupMap = new HashMap<>();

    private final String pathXml = "data/group/data_group.xml";

    private final String pathJson = "data/group/data_group.json";

    private final String pathBin = "data/group/data_group.tmp";

    @Override
    public void addGroup(final Group group) {
        groupMap.put(group.getId(), group);
    }

    @Override
    public Group updateGroup(final Group group) {
        return groupMap.put(group.getId(), group);
    }

    @Override
    public Group deleteGroup(final String id) {
        return groupMap.remove(id);
    }

    @Override
    public List<Group> getGroupList() {
        return new ArrayList<>(groupMap.values());
    }

    @Override
    public Group getGroup(final Integer index) {
        return getGroupList().get(index);
    }

    public Group getGroupById(final String id) {
        return groupMap.get(id);
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
        for (Group group : tasks) groupMap.put(group.getId(), group);
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
