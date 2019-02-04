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
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@ApplicationScoped
public class TaskService extends AbstractService<ITaskRepository, Task> implements ITaskService {

    @Inject
    private IAssigneeTaskRepository assigneeTaskRepository;

    public Task getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.findBy(id);
    }

    @Override
    public void add(@Nullable Task task, @Nullable User user) {
        if (task == null || user == null) throw new WrongInputException("Wrong Input");
        repository.save(task);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.save(assigneeTask);
    }

    public void delete(@Nullable Task entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.remove(entity);
    }

    @Override
    public void completeTask(@Nullable final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        task.setComplete(true);
        repository.refresh(task);
    }

    @Override
    public void clearTask(@Nullable List<Task> taskList) {
        if (taskList == null) throw new WrongInputException("Wrong input");
        for (Task task : taskList) {
            if (task.isComplete())
                repository.remove(task);
        }
    }

    @Override
    public void setGroup(@Nullable final Task task, @Nullable final Group group) throws WrongInputException {
        if (task == null || group == null) throw new WrongInputException("Wrong input");
        task.setGroup(group);
        repository.saveAndFlushAndRefresh(task);
    }

    @Override
    public List<Task> getListTaskByUserId(@Nullable User user) {
        if (user == null) throw new WrongInputException("Wrong Input");
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        try {
            return repository.getListTaskByUserId(user.getId());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void linkToTask(@Nullable User user, @Nullable Task task) {
        if (user == null || task == null) throw new WrongInputException("Wrong input");
        task.setUser(user);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.save(assigneeTask);
    }

}
