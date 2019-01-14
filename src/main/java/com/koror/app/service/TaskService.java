package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.TaskRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService extends AbstractService<TaskRepository, Task> implements ITaskRepository, IDataIO {

    private final AssigneeTaskService assigneeTaskService;

    private final String pathXml = "data/task/data_task.xml";

    private final String pathJson = "data/task/data_task.json";

    private final String pathBin = "data/task/data_task.tmp";

    public TaskService(TaskRepository taskRepository, AssigneeTaskService assigneeTaskService) {
        this.repository = taskRepository;
        this.assigneeTaskService = assigneeTaskService;
    }

    @Override
    public void completeTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        repository.completeTask(task);
    }

    @Override
    public void updateTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        repository.updateTask(task);
    }

    @Override
    public void clearTask(List<Task> taskList) {
        repository.clearTask(taskList);
    }

    @Override
    public void setGroupId(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        repository.setGroupId(task);
    }

    @Override
    public Task getTaskByIndex(Integer index) {
        return repository.getTaskByIndex(index);
    }

    public List<Task> getListTaskByUser(User user) {
        if(user.getAccess()== Access.ADMIN) return getList();
        final List<Task> taskList = new ArrayList<>();
        for (final AssigneeTask assigneeTask : assigneeTaskService.getList()) {
            if (user.getId().equals(assigneeTask.getUserId())) {
                taskList.add(getById(assigneeTask.getTaskId()));
            }
        }
        return taskList;
    }

    @Override
    public void saveDataSerialization() throws IOException {
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(getList());
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException {
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List tasks = (List) ois.readObject();
        for (Object task : tasks)
            if (task instanceof Task) add((Task) task);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final Task[] listTask = getList().toArray(new Task[getList().size()]);
        objectMapper.writeValue(new File(pathXml), listTask);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final Task[] listTask = objectMapper.readValue(new File(pathXml), Task[].class);
        for (Task task : listTask) add(task);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File("data/task/").mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Task[] listTask = getList().toArray(new Task[getList().size()]);
        objectMapper.writeValue(new File(pathJson), listTask);
    }

    @Override
    public void loadDataJson() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Task[] listTask = objectMapper.readValue(new File(pathJson), Task[].class);
        for (Task task : listTask) add(task);
    }

}
