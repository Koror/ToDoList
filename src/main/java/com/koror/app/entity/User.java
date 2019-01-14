package com.koror.app.entity;

import com.koror.app.enumerated.Access;

import java.io.Serializable;
import java.util.UUID;

public class User extends AbstractEntity implements Serializable {

    private String login;

    private String password;

    private String name = "Default name";

    private String email;

    private Access access = Access.USER;

    public User(String login, String password) {
        this.login = login;
        this.password = hashPassword(password);
    }

    public static String hashPassword(String password) {
        return Integer.toString(password.hashCode());
    }

    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        name = newName;
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
