package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.repository.AssigneeGroupRepository;

import java.io.*;
import java.util.List;


public class AssigneeGroupService implements IAssigneeGroupRepository, IDataIO {

    private final AssigneeGroupRepository assigneeGroupRepository;

    private final String pathXml = "data/assignee_group/data_assignee_group.xml";

    private final String pathJson = "data/assignee_group/data_assignee_group.json";

    private final String pathBin = "data/assignee_group/data_assignee_group.tmp";

    public AssigneeGroupService(AssigneeGroupRepository assigneeGroupRepository){
        this.assigneeGroupRepository = assigneeGroupRepository;
    }

    @Override
    public void addAssignee(AssigneeGroup assigneeGroup) {
        assigneeGroupRepository.addAssignee(assigneeGroup);
    }

    @Override
    public void deleteAssignee(String id) {
        assigneeGroupRepository.deleteAssignee(id);
    }

    @Override
    public AssigneeGroup getAssigneeById(String id) {
        return assigneeGroupRepository.getAssigneeById(id);
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId) {
        return assigneeGroupRepository.getAssigneeByUserId(userId);
    }

    @Override
    public void saveDataSerialization() throws IOException {
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File("data/assignee_group/").mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(getAssigneeGroupList());
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException{
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List<AssigneeGroup> assigneeGroups = (List<AssigneeGroup>) ois.readObject();
        for (AssigneeGroup assigneeGroup : assigneeGroups) addAssignee(assigneeGroup);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File("data/assignee_group/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final AssigneeGroup[] listAssignee = getAssigneeGroupList().toArray(new AssigneeGroup[getAssigneeGroupList().size()]);
        objectMapper.writeValue(new File(pathXml), listAssignee);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final AssigneeGroup[] listAssignee = objectMapper.readValue(new File(pathXml), AssigneeGroup[].class);
        for (AssigneeGroup assigneeGroup : listAssignee) addAssignee(assigneeGroup);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File("data/assignee_group/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final AssigneeGroup[] listAssignee = getAssigneeGroupList().toArray(new AssigneeGroup[getAssigneeGroupList().size()]);
        objectMapper.writeValue(new File(pathJson), listAssignee);
    }

    @Override
    public void loadDataJson() throws IOException{
        final ObjectMapper objectMapper = new ObjectMapper();
        final AssigneeGroup[] listAssignee = objectMapper.readValue(new File(pathJson), AssigneeGroup[].class);
        for (AssigneeGroup assigneeGroup : listAssignee) addAssignee(assigneeGroup);
    }

    @Override
    public List<AssigneeGroup> getAssigneeGroupList() {
        return assigneeGroupRepository.getAssigneeGroupList();
    }

}
