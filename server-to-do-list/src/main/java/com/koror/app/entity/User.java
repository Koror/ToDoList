package com.koror.app.entity;

import com.koror.app.enumerated.Access;
import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

public class User extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    private String login;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private Access access = Access.USER;

    public User(){

    }

    public User(String login, String password) {
        this.login = login;
        this.password = Hash.getHashString(password);;
    }

    public void setHashPassword(String password){
        this.password = Hash.getHashString(password);
    }

    @Override
    public String toString() {
        return login;
    }
}
