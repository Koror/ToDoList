package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.UserRepository;

import java.io.*;
import java.util.List;

public class UserService implements IUserRepository{

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void add(User entity) {
        if(entity==null) throw new WrongInputException("Wrong Input");
        repository.add(entity);
    }

    public void delete(String id) {
        if(id==null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        repository.delete(id);
    }

    public User getById(String id) {
        if(id==null || id.isEmpty()) return null;
        return repository.getById(id);
    }

    public List<User> getList() {
        return repository.getList();
    }

    public void update(final User entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.update(entity);
    }

    @Override
    public User getByLogin(String login) {
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        return repository.getByLogin(login);
    }

    @Override
    public User login(String login, String password){
        User user = repository.login(login, password);
        if(user == null) throw new UserNotExistsException();
        return user;
    }

}
