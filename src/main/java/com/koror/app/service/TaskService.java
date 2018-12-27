package com.koror.app.service;

import com.koror.app.entity.Task;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.TaskRepository;

import java.util.List;
import java.util.Map;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(final Task task) throws WrongInputException {
        if (task == null)
            throw new WrongInputException();
        taskRepository.addTask(task);
    }

    public void completeTask(final Task task) throws WrongInputException {
        if (task == null)
            throw new WrongInputException();
        taskRepository.completeTask(task);
    }

    public void deleteTask(final String id) throws WrongInputException {
        try {
            taskRepository.deleteTask(id);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new WrongInputException();
        }
    }

    public void updateTask(final Task task) throws WrongInputException {
        if (task == null)
            throw new WrongInputException();
        taskRepository.updateTask(task);
    }

    public void clearTask() {
        taskRepository.clearTask();
    }

    public void setGroupId(final Task task) throws WrongInputException {
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
