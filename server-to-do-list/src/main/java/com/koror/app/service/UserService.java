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
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@ApplicationScoped
public class UserService extends AbstractService<IUserRepository, User> implements IUserService {

    @Inject
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Override
    public void delete(@Nullable String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        repository.delete(id);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public User getById(@Nullable String id) {
        if(id==null || id.isEmpty()) return null;
        entityManager.getTransaction().begin();
        User user = repository.getById(id);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return user;
    }

    @Override
    public List<User> getList() {
        entityManager.getTransaction().begin();
        List<User> userList = repository.getList();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return userList;
    }

    @Override
    public User getByLogin(@Nullable String login) {
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        User user = repository.getByLogin(login);
        entityManager.clear();
        return user;
    }

    @Override
    public User login(@Nullable String login, @Nullable String password){
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        if(password == null || password.isEmpty()) throw new WrongInputException("Wrong input");
        entityManager.getTransaction().begin();
        final String hashPassword = Hash.createHashString(password);
        User user = null;
        for(User userTemp : repository.getList()){
            if(login.equals(userTemp.getLogin()) && hashPassword.equals(userTemp.getPassword()))
                user = repository.getById(userTemp.getId());
        }
        if(user == null) throw new UserNotExistsException();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return user;
    }

    public void linkToTask(@Nullable User user, @Nullable Task task){
        if(user == null || task == null) throw new WrongInputException("Wrong input");
        entityManager.getTransaction().begin();
        task.setUser(user);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.add(assigneeTask);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

}
