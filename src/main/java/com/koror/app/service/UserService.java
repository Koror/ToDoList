package com.koror.app.service;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.repository.UserRepository;

import java.util.List;

public class UserService implements IUserRepository {

    private final UserRepository userRepository;

    public UserService(UserRepository repository){
        userRepository = repository;
    }
    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.getUserList();
    }

}
