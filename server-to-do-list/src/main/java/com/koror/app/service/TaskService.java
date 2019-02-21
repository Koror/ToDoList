package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.service.ITaskService;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
public class TaskService implements ITaskService {

    @Autowired
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Autowired
    private ITaskRepository iTaskRepository;

    @Override
    public void add(@Nullable final Task entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iTaskRepository.save(entity);
    }

    @Override
    public void update(@Nullable final Task entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        iTaskRepository.save(entity);
    }

    @Override
    public List<Task> getList() {
        return iTaskRepository.findAll();
    }

    @Override
    public Task getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return iTaskRepository.findById(id).get();
    }

    @Override
    public void add(@Nullable Task task, @Nullable User user) {
        if (task == null || user == null) throw new WrongInputException("Wrong Input");
        iTaskRepository.save(task);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.save(assigneeTask);
    }

    @Override
    public void delete(@Nullable Task entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iTaskRepository.delete(entity);
    }

    @Override
    public void completeTask(@Nullable final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        task.setComplete(true);
        iTaskRepository.save(task);
    }

    @Override
    public void clearTask(@Nullable List<Task> taskList) {
        if (taskList == null) throw new WrongInputException("Wrong input");
        for (Task task : taskList) {
            if (task.isComplete())
                iTaskRepository.delete(task);
        }
    }

    @Override
    public void setGroup(@Nullable final Task task, @Nullable final Group group) throws WrongInputException {
        if (task == null || group == null) throw new WrongInputException("Wrong input");
        task.setGroup(group);
        iTaskRepository.save(task);
    }

    @Override
    public List<Task> getListTaskByUserId(@Nullable String userId) {
        if (userId == null || userId.isEmpty()) throw new WrongInputException("Wrong Input");
        //if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        try {
            return iTaskRepository.getListTaskByUserId(userId);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Task> getListTaskByGroupId(@Nullable String groupId) {
        if (groupId == null) throw new WrongInputException("Wrong Input");
        try {
            return iTaskRepository.getListTaskByGroupId(groupId);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void linkToTask(@Nullable User user, @Nullable Task task) {
        if (user == null || task == null) throw new WrongInputException("Wrong input");
        task.setUser(user);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.save(assigneeTask);
    }

}
