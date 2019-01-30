package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.service.ITaskService;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TaskService extends AbstractService<ITaskRepository, Task> implements ITaskService {

    @Inject
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Override
    public void add(@Nullable Task task, @Nullable User user) {
        if (task == null || user == null) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        repository.add(task);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.add(assigneeTask);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void delete(@Nullable String taskId, @Nullable String userId) {
        if (taskId == null || taskId.isEmpty()) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        assigneeTaskRepository.deleteAssigneeByParam(userId, taskId);
        repository.delete(taskId);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public Task getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        Task task = repository.getById(id);
        entityManager.clear();
        return task;
    }

    @Override
    public List<Task> getList() {
        List<Task> taskList = repository.getList();
        entityManager.clear();
        return taskList;
    }

    @Override
    public void completeTask(@Nullable final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        entityManager.getTransaction().begin();
        task.setComplete(true);
        repository.update(task);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void clearTask(@Nullable List<Task> taskList) {
        if (taskList == null) throw new WrongInputException("Wrong input");
        entityManager.getTransaction().begin();
        for (Task task : taskList) {
            if (task.isComplete())
                repository.delete(task.getId());
        }
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void setGroup(@Nullable final Task task, @Nullable final Group group) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        entityManager.getTransaction().begin();
        task.setGroup(group);
        repository.update(task);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public List<Task> getListTaskByUserId(@Nullable String userId) {
        List<Task> taskList = repository.getListTaskByUserId(userId);
        entityManager.clear();
        return taskList;
    }


}
