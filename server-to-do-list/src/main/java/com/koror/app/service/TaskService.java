package com.koror.app.service;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.repository.IUserRepository;
import com.koror.app.api.service.ITaskService;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;

import java.util.List;

public class TaskService extends AbstractService implements ITaskService {

    private final ITaskRepository taskRepository;

    private final IUserRepository userRepository;

    private final AssigneeTaskService assigneeTaskService;

    public TaskService(ITaskRepository taskRepository, IUserRepository userRepository, AssigneeTaskService assigneeTaskService) {
        this.taskRepository = taskRepository;
        this.assigneeTaskService = assigneeTaskService;
        this.userRepository = userRepository;
    }

    @Override
    public void add(Task entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        hibernateSession.getTransaction().begin();
        taskRepository.add(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        hibernateSession.getTransaction().begin();
        taskRepository.delete(id);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public Task getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        hibernateSession.getTransaction().begin();
        Task task = taskRepository.getById(id);
        hibernateSession.getTransaction().commit();
        return task;
    }

    @Override
    public List<Task> getList() {
        hibernateSession.getTransaction().begin();
        List<Task> taskList = taskRepository.getList();
        hibernateSession.getTransaction().commit();
        return taskList;
    }

    @Override
    public void update(final Task entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        hibernateSession.getTransaction().begin();
        taskRepository.update(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public void completeTask(final Task task) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        hibernateSession.getTransaction().begin();
        task.setComplete(true);
        taskRepository.update(task);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public void clearTask(List<Task> taskList) {
        hibernateSession.getTransaction().begin();
        for (Task task : taskList) {
            if (task.isComplete())
                taskRepository.delete(task.getId());
        }
        hibernateSession.getTransaction().commit();
    }

    @Override
    public void setGroup(final Task task, Group group) throws WrongInputException {
        if (task == null) throw new WrongInputException("Wrong input");
        hibernateSession.getTransaction().begin();
        task.setGroup(group);
        taskRepository.update(task);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public List<Task> getListTaskByUser(User user) {
        return null;
    }

    @Override
    public List<Task> getListTaskByUserId(String userId) {
        hibernateSession.getTransaction().begin();
        List<Task> taskList = taskRepository.getListTaskByUserId(userId);
        hibernateSession.getTransaction().commit();
        return taskList;
    }


}
