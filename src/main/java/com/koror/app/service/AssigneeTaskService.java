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

public class AssigneeTaskService implements IAssigneeTaskRepository, IDataIO {

    private final AssigneeTaskRepository assigneeTaskRepository;

    private final String pathXml = "data/assignee_task/data_assignee_task.xml";

    private final String pathJson = "data/assignee_task/data_assignee_task.json";

    private final String pathBin = "data/assignee_task/data_assignee_task.tmp";

    public AssigneeTaskService(AssigneeTaskRepository assigneeTaskRepository) {
        this.assigneeTaskRepository = assigneeTaskRepository;
    }

    @Override
    public void addAssignee(AssigneeTask assigneeTask) {
        if(assigneeTask==null) throw new WrongInputException("Wrong Input");
        assigneeTaskRepository.addAssignee(assigneeTask);
    }

    @Override
    public void deleteAssignee(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        assigneeTaskRepository.deleteAssignee(id);
    }

    @Override
    public void deleteAssigneeByParam(String userId, String taskId) {
        if(userId==null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        if(taskId==null || taskId.isEmpty()) throw new WrongInputException("Wrong Input");
        assigneeTaskRepository.deleteAssigneeByParam(userId, taskId);
    }

    @Override
    public AssigneeTask getAssigneeById(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return assigneeTaskRepository.getAssigneeById(id);
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        if(userId==null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        return assigneeTaskRepository.getAssigneeByUserId(userId);
    }

    @Override
    public void saveDataSerialization() throws IOException {
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File("data/assignee_task/").mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(getAssigneeTaskList());
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException{
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List<AssigneeTask> assigneeTasks = (List<AssigneeTask>) ois.readObject();
        for (AssigneeTask assigneeTask : assigneeTasks) addAssignee(assigneeTask);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File("data/assignee_task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final AssigneeTask[] listAssignee = getAssigneeTaskList().toArray(new AssigneeTask[getAssigneeTaskList().size()]);
        objectMapper.writeValue(new File(pathXml), listAssignee);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final AssigneeTask[] listAssignee = objectMapper.readValue(new File(pathXml), AssigneeTask[].class);
        for (AssigneeTask assigneeTask : listAssignee) addAssignee(assigneeTask);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File("data/assignee_task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final AssigneeTask[] listAssignee = getAssigneeTaskList().toArray(new AssigneeTask[getAssigneeTaskList().size()]);
        objectMapper.writeValue(new File(pathJson), listAssignee);
    }

    @Override
    public void loadDataJson() throws IOException{
        final ObjectMapper objectMapper = new ObjectMapper();
        final AssigneeTask[] listAssignee = objectMapper.readValue(new File(pathJson), AssigneeTask[].class);
        for (AssigneeTask assigneeTask : listAssignee) addAssignee(assigneeTask);
    }

    @Override
    public List<AssigneeTask> getAssigneeTaskList() {
        return assigneeTaskRepository.getAssigneeTaskList();
    }

}
