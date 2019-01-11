package com.koror.app.entity;

import com.koror.app.enumerated.Access;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String login;

    private String password;

    private String email;

    private String name;

    private Access access = Access.USER;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = hashPassword(password);
    }

    public static String hashPassword(String password) {
        return Integer.toString(password.hashCode());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return login;
    }
}
