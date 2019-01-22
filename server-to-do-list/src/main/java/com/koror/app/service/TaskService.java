package com.koror.app.service;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.service.ITaskService;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;

import java.util.ArrayList;
import java.util.List;

public class TaskService implements ITaskService {

    private final ITaskRepository repository;

    private final AssigneeTaskService assigneeTaskService;

    public TaskService(ITaskRepository taskRepository, AssigneeTaskService assigneeTaskService) {
        this.repository = taskRepository;
        this.assigneeTaskService = assigneeTaskService;
    }

    @Override
    public void add(Task entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    @Override
    public Task getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    @Override
    public List<Task> getList() {
        return repository.getList();
    }

    @Override
    public void update(final Task entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    @Override
    public void completeTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        task.setComplete(true);
        repository.update(task);
    }

    @Override
    public void clearTask(List<Task> taskList) {
        for (Task task : taskList) {
            if (task.isComplete())
                repository.delete(task.getId());
        }
    }

    @Override
    public void setGroupId(final Task task, String groupId) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        task.setGroupId(groupId);
        repository.update(task);
    }

    @Override
    public List<Task> getListTaskByUser(User user) {
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        final List<Task> taskList = new ArrayList<>();
        for (final AssigneeTask assigneeTask : assigneeTaskService.getList()) {
            if (user.getId().equals(assigneeTask.getUserId())) {
                taskList.add(getById(assigneeTask.getTaskId()));
            }
        }
        return taskList;
    }

}
