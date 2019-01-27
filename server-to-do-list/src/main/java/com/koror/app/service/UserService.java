package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.IUserRepository;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;
import com.koror.app.error.WrongInputException;
import com.koror.app.util.Hash;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserService extends AbstractService<IUserRepository, User> implements IUserService {

    private IAssigneeTaskRepository assigneeTaskRepository;

    public UserService(IUserRepository repository,IAssigneeTaskRepository assigneeTaskRepository,  EntityManagerFactory entityManagerFactory) {
        this.repository = repository;
        this.assigneeTaskRepository = assigneeTaskRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void delete(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        repository.delete(id, entityManager);
    }

    @Override
    public User getById(String id) {
        if(id==null || id.isEmpty()) return null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = repository.getById(id, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public List<User> getList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> userList = repository.getList(entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
        return userList;
    }

    @Override
    public User getByLogin(String login) {
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = repository.getByLogin(login, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public User login(String login, String password){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        final String hashPassword = Hash.createHashString(password);
        User user = null;
        for(User userTemp : repository.getList(entityManager)){
            if(login.equals(userTemp.getLogin()) && hashPassword.equals(userTemp.getPassword()))
                user = repository.getById(userTemp.getId(), entityManager);
        }
        if(user == null) throw new UserNotExistsException();
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    public void linkToTask(User user, Task task){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        task.setUser(user);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.add(assigneeTask, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
