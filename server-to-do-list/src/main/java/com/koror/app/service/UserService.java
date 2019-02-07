package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.IUserRepository;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;
import com.koror.app.error.WrongInputException;
import com.koror.app.util.Hash;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public void add(@Nullable final User entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iUserRepository.save(entity);
    }

    @Override
    public void update(@Nullable final User entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        iUserRepository.save(entity);
    }

    @Override
    public List<User> getList() {
        return iUserRepository.findAll();
    }

    @Override
    public User getByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        User user = null;
        try {
            user = iUserRepository.findByLogin(login);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return iUserRepository.findById(id).get();
    }

    @Override
    public void delete(@Nullable User entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iUserRepository.delete(entity);
    }

    @Override
    public User login(@Nullable String login, @Nullable String password) {
        if (login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        if (password == null || password.isEmpty()) throw new WrongInputException("Wrong input");
        final String hashPassword = Hash.createHashString(password);
        User user = null;
        for (User userTemp : iUserRepository.findAll()) {
            if (login.equals(userTemp.getLogin()) && hashPassword.equals(userTemp.getPassword()))
                user = userTemp;
        }
        if (user == null) throw new UserNotExistsException();
        return user;
    }

}
