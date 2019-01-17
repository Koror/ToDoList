package com.koror.app.service;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskService implements ITaskRepository {

    private final TaskRepository repository;

    private final AssigneeTaskService assigneeTaskService;


    public TaskService(TaskRepository taskRepository, AssigneeTaskService assigneeTaskService) {
        this.repository = taskRepository;
        this.assigneeTaskService = assigneeTaskService;
    }

    public void add(Task entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    public Task getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    public List<Task> getList() {
        return repository.getList();
    }

    public void update(final Task entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    @Override
    public void completeTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        repository.completeTask(task);
    }

    @Override
    public void clearTask(List<Task> taskList) {
        repository.clearTask(taskList);
    }

    @Override
    public void setGroupId(final Task task, String groupId) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        repository.setGroupId(task, groupId);
    }

    @Override
    public Task getTaskByIndex(Integer index) {
        return repository.getTaskByIndex(index);
    }

    public List<Task> getListTaskByUser(User user) {
        if (user.getAccess() == Access.ADMIN) return getList();
        final List<Task> taskList = new ArrayList<>();
        for (final AssigneeTask assigneeTask : assigneeTaskService.getList()) {
            if (user.getId().equals(assigneeTask.getUserId())) {
                taskList.add(getById(assigneeTask.getTaskId()));
            }
        }
        return taskList;
    }

}
