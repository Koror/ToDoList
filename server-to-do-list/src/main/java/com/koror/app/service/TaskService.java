package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.repository.IUserRepository;
import com.koror.app.api.service.ITaskService;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeTaskRepository;
import com.koror.app.repository.TaskRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class TaskService extends AbstractService<ITaskRepository, Task> implements ITaskService {


    private IAssigneeTaskRepository assigneeTaskRepository;

    public TaskService(ITaskRepository repository, IAssigneeTaskRepository assigneeTaskRepository, EntityManagerFactory entityManagerFactory) {
        this.repository = repository;
        this.assigneeTaskRepository = assigneeTaskRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(@Nullable Task task, @Nullable User user) {
        if(task==null) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        repository.add(task, entityManager);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.add(assigneeTask, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(@Nullable String taskId,@Nullable String userId) {
        if (taskId == null || taskId.isEmpty()) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        assigneeTaskRepository.deleteAssigneeByParam(userId, taskId, entityManager);
        repository.delete(taskId, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Task getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task task = repository.getById(id, entityManager);
        entityManager.close();
        return task;
    }

    @Override
    public List<Task> getList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Task> taskList = repository.getList(entityManager);
        entityManager.close();
        return taskList;
    }

    @Override
    public void completeTask(@Nullable final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        task.setComplete(true);
        repository.update(task, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void clearTask(@Nullable List<Task> taskList) {
        if (taskList == null) throw new WrongInputException("Wrong input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Task task : taskList) {
            if (task.isComplete())
                repository.delete(task.getId(), entityManager);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void setGroup(@Nullable final Task task,@Nullable final Group group) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        task.setGroup(group);
        repository.update(task, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Task> getListTaskByUserId(@Nullable String userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Task> taskList = repository.getListTaskByUserId(userId, entityManager);
        entityManager.close();
        return taskList;
    }


}
