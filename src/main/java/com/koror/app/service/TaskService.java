package com.koror.app.service;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.TaskRepository;

import java.io.IOException;
import java.util.List;

public class TaskService implements ITaskRepository {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.addTask(task);
    }

    @Override
    public void completeTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.completeTask(task);
    }

    @Override
    public Task deleteTask(final String id) throws WrongInputException {
        if (id == null || "".equals(id)) throw new WrongInputException("Wrong input");
        Task task = taskRepository.deleteTask(id);
        if (task == null) throw new WrongInputException("Wrong input");
        return task;
    }

    @Override
    public void updateTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.updateTask(task);
    }

    @Override
    public void clearTask() {
        taskRepository.clearTask();
    }

    @Override
    public void setGroupId(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        taskRepository.setGroupId(task);
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public Task getTaskByIndex(Integer index) {
        return taskRepository.getTaskByIndex(index);
    }

    @Override
    public void saveDataSerialization() throws IOException{
        taskRepository.saveDataSerialization();
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException{
        taskRepository.loadDataSerialization();
    }

    @Override
    public void saveDataXml() throws IOException {
        taskRepository.saveDataXml();
    }

    @Override
    public void loadDataXml() throws IOException{
        taskRepository.loadDataXml();
    }

    @Override
    public void saveDataJson() throws IOException{
        taskRepository.saveDataJson();
    }

    @Override
    public void loadDataJson() throws IOException{
        taskRepository.loadDataJson();
    }

}
