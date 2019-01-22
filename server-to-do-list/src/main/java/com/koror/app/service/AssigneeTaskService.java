package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.service.IAssigneeTaskService;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.error.WrongInputException;
import com.koror.app.util.HibernateFactory;

import java.util.List;

public class AssigneeTaskService implements IAssigneeTaskService {

    private final IAssigneeTaskRepository repository;

    public AssigneeTaskService(IAssigneeTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(AssigneeTask entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    @Override
    public AssigneeTask getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getById(id);
    }

    @Override
    public List<AssigneeTask> getList() {
        return repository.getList();
    }

    @Override
    public void update(final AssigneeTask entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    @Override
    public void deleteAssigneeByParam(String userId, String taskId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        if (taskId == null || taskId.isEmpty()) throw new WrongInputException("Wrong Input");
        for(AssigneeTask assigneeTaskTemp : repository.getList()){
            if(userId.equals(assigneeTaskTemp.getUserId()) && taskId.equals(assigneeTaskTemp.getTaskId()))
                repository.delete(assigneeTaskTemp.getId());
        }
    }


    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.getAssigneeByUserId(userId);
    }

}
