package com.koror.app.api.service;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;

public interface IUserService extends IUserRepository {

    User login(String login, String password);

}
