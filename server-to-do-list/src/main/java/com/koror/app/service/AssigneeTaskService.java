package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeTaskRepository;

import java.io.*;
import java.util.List;
import java.util.Map;

public class AssigneeTaskService extends AbstractService<AssigneeTaskRepository, AssigneeTask> implements IAssigneeTaskRepository, IDataIO {

    private final String pathXml = "data/assignee_task/data_assignee_task.xml";

    private final String pathJson = "data/assignee_task/data_assignee_task.json";

    private final String pathBin = "data/assignee_task/data_assignee_task.tmp";

    private final String pathPackage = "data/assignee_task/";

    public AssigneeTaskService(AssigneeTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAssigneeByParam(String userId, String taskId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        if (taskId == null || taskId.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.deleteAssigneeByParam(userId, taskId);
    }


    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getAssigneeByUserId(userId);
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
        final List<AssigneeTask> assigneeTasks = (List<AssigneeTask>) ois.readObject();
        for (AssigneeTask assigneeTask : assigneeTasks) add(assigneeTask);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final AssigneeTask[] listAssignee = getList().toArray(new AssigneeTask[getList().size()]);
        objectMapper.writeValue(new File(pathXml), listAssignee);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final AssigneeTask[] listAssignee = objectMapper.readValue(new File(pathXml), AssigneeTask[].class);
        for (AssigneeTask assigneeTask : listAssignee) add(assigneeTask);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final AssigneeTask[] listAssignee = getList().toArray(new AssigneeTask[getList().size()]);
        objectMapper.writeValue(new File(pathJson), listAssignee);
    }

    @Override
    public void loadDataJson() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final AssigneeTask[] listAssignee = objectMapper.readValue(new File(pathJson), AssigneeTask[].class);
        for (AssigneeTask assigneeTask : listAssignee) add(assigneeTask);
    }

}
