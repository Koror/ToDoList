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
    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void deleteUser(String id) {
        userMap.remove(id);
    }

    @Override
    public List<User> getUserList() {
        return new ArrayList<>(userMap.values());
    }

}
