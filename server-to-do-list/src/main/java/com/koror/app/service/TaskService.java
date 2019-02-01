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

    @Override
    public void add(@Nullable Task task, @Nullable User user) {
        if (task == null || user == null) throw new WrongInputException("Wrong Input");
        repository.save(task);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.save(assigneeTask);
    }

    @Override
    public void delete(@Nullable Task task, @Nullable User user) {
        if (task == null || user == null) throw new WrongInputException("Wrong Input");
        //assigneeTaskRepository.getAssigneeByParam(user.getId(), task.getId());
        repository.remove(task);
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
        if (task == null) throw new WrongInputException("Wrong input");
        task.setGroup(group);
        repository.refresh(task);
    }

    @Override
    public List<Task> getListTaskByUserId(@Nullable User user) {
        if (user == null) throw new WrongInputException("Wrong Input");
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        try {
            return repository.getListTaskByUserId(user.getId());
        }catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
