package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeTaskRepository;

import java.util.List;

public class AssigneeTaskService implements IAssigneeTaskRepository {

    private final AssigneeTaskRepository repository;

    public AssigneeTaskService(AssigneeTaskRepository repository) {
        this.repository = repository;
    }

    public void add(AssigneeTask entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    public AssigneeTask getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    public List<AssigneeTask> getList() {
        return repository.getList();
    }

    public void update(final AssigneeTask entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
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

}
