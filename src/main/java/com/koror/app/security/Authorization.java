package com.koror.app.security;

import com.koror.app.entity.User;

public class Authorization {

    User user;

    public void auth(User user){
        this.user = user;
    }

    public void logout(){
        user = null;
    }

    public User getUser() {
        return user;
    }
}
