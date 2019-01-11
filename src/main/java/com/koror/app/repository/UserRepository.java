package com.koror.app.repository;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository implements IUserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public void registerUser(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void loadUser(User user) {
        boolean userExist = false;
        for (User userTemp : getUserList())
            if (user.getLogin().equals(userTemp.getLogin()))
                userExist = true;
        if (!userExist)
            userMap.put(user.getId(), user);
    }

    @Override
    public void deleteUserById(String id) {
        userMap.remove(id);
    }

    @Override
    public List<User> getUserList() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User findById(String id) {
        return userMap.get(id);
    }

    @Override
    public User findByLogin(final String login) {
        for (User user : userMap.values()) {
            if (login.equals(user.getLogin()))
                return user;
        }
        return null;
    }

}
