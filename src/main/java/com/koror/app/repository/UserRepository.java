package com.koror.app.repository;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;

import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public void loadUser(User user) {
        boolean userExist = false;
        for (User userTemp : getList())
            if (user.getLogin().equals(userTemp.getLogin()))
                userExist = true;
        if (!userExist)
            mapEntity.put(user.getId(), user);
    }

    @Override
    public User getByLogin(final String login) {
        for (User user : mapEntity.values()) {
            if (login.equals(user.getLogin()))
                return user;
        }
        return null;
    }

    @Override
    public User login(String login, String password){
        User user=null;
        password = User.hashPassword(password);
        for (User userTemp : getList()){
            if((login.equals(userTemp.getLogin())) && (password.equals(userTemp.getPassword())))
                user = userTemp;
        }
        return user;
    }

}
