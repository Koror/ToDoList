package com.koror.app.service;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;
import com.koror.app.error.WrongInputException;
import com.koror.app.util.Hash;

import java.util.List;

public class UserService extends AbstractService implements IUserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(User user) {
        if(user==null) throw new WrongInputException("Wrong Input");
        hibernateSession.getTransaction().begin();
        repository.add(user);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        hibernateSession.getTransaction().begin();
        repository.delete(id);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public User getById(String id) {
        if(id==null || id.isEmpty()) return null;
        hibernateSession.getTransaction().begin();
        User user = repository.getById(id);
        hibernateSession.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> getList() {
        hibernateSession.getTransaction().begin();
        List<User> userList = repository.getList();
        hibernateSession.getTransaction().commit();
        return userList;
    }

    @Override
    public void update(final User entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        hibernateSession.getTransaction().begin();
        repository.update(entity);
        hibernateSession.getTransaction().commit();
    }

    @Override
    public User getByLogin(String login) {
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        hibernateSession.getTransaction().begin();
        User user = repository.getByLogin(login);
        hibernateSession.getTransaction().commit();
        return user;
    }

    @Override
    public User login(String login, String password){
        hibernateSession.getTransaction().begin();
        final String hashPassword = Hash.createHashString(password);
        User user = null;
        for(User userTemp : repository.getList()){
            if(login.equals(userTemp.getLogin()) && hashPassword.equals(userTemp.getPassword()))
                user = repository.getById(userTemp.getId());
        }
        if(user == null) throw new UserNotExistsException();
        hibernateSession.getTransaction().commit();
        return user;
    }

}
