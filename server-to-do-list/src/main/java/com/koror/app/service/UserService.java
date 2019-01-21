package com.koror.app.service;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;
import com.koror.app.error.WrongInputException;
import com.koror.app.util.Hash;
import com.koror.app.util.Transaction;

import java.util.List;

public class UserService implements IUserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(User entity) {
        if(entity==null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
        Transaction.commit();
    }

    @Override
    public void delete(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
        Transaction.commit();
    }

    @Override
    public User getById(String id) {
        if(id==null || id.isEmpty()) return null;
        return repository.getById(id);
    }

    @Override
    public List<User> getList() {
        return repository.getList();
    }

    @Override
    public void update(final User entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
        Transaction.commit();
    }

    @Override
    public User getByLogin(String login) {
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        return repository.getByLogin(login);
    }

    @Override
    public User login(String login, String password){
        final String hashPassword = Hash.getHashString(password);
        User user = repository.login(login, hashPassword);
        if(user == null) throw new UserNotExistsException();
        Transaction.commit();
        return user;
    }

}
