package com.koror.app.service;

import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

import java.util.List;
import java.util.Map;

public class TaskService {

    private final TaskRepository taskRepository;

    private final GroupRepository groupRepository;

    public TaskService(TaskRepository taskRepository, GroupRepository groupRepository) {
        this.taskRepository = taskRepository;
        this.groupRepository = groupRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    public void addTask(Task task) throws WrongInputException {
        if (task == null)
            throw new WrongInputException();
        taskRepository.addTask(task);
    }

    public void completeTask(Task task) throws WrongInputException {
        if (task == null)
            throw new WrongInputException();
        taskRepository.completeTask(task);
    }

    public void deleteTask(String id) throws WrongInputException {
        if (id == null)
            throw new WrongInputException();
        taskRepository.deleteTask(id);
    }

    public void updateTask(Task task) throws WrongInputException {
        if (task == null)
            throw new WrongInputException();
        taskRepository.updateTask(task);
    }

    public void clearTask() {
        taskRepository.clearTask();
    }

    public void setGroupId(Task task) throws WrongInputException {
        if (task == null)
            throw new WrongInputException();
        taskRepository.setGroupId(task);
    }

    public List<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    public Map<String, Task> getTaskMap() {
        return taskRepository.getTaskMap();
    }

}
