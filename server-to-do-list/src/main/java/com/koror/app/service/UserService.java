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
import org.apache.deltaspike.data.api.QueryInvocationException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@ApplicationScoped
public class UserService extends AbstractService<IUserRepository, User> implements IUserService {

    @Inject
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Override
    public User getByLogin(@Nullable String login){
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        User user = null;
        try {
            user = repository.findByLogin(login);
        }catch (Throwable e){
           System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User login(@Nullable String login, @Nullable String password){
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        if(password == null || password.isEmpty()) throw new WrongInputException("Wrong input");
        final String hashPassword = Hash.createHashString(password);
        User user = null;
        for(User userTemp : repository.findAll()){
            if(login.equals(userTemp.getLogin()) && hashPassword.equals(userTemp.getPassword()))
                user = userTemp;
        }
        if(user == null) throw new UserNotExistsException();
        return user;
    }

    public void linkToTask(@Nullable User user, @Nullable Task task){
        if(user == null || task == null) throw new WrongInputException("Wrong input");
        task.setUser(user);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        assigneeTaskRepository.save(assigneeTask);
    }

}
