package com.koror.app.security;

public class Authorization {

    private String login;

    private String password;

    public void auth(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
