package com.koror.app.service;

import com.koror.app.database.DatabaseConnection;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.repository.UserRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void addUser() {
        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);
        User user = new User();
        user.setAccess(Access.USER);
        user.setEmail("mail.ru");
        user.setName("TemplarAssassin");
        user.setPassword("12345");
        user.setLogin("Lanaya");
        service.add(user);
        DatabaseConnection.closeConnection();
    }

    @Test
    public void loadUser() {
    }

    @Test
    public void deleteUserById() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getByLogin() {
    }
}