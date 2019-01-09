package com.koror.app.security;

public class Authorization {

    private String login;

    private String password;

    private String userId;

    public void auth(String login, String password, String userId){
        this.login = login;
        this.password = password;
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

}
