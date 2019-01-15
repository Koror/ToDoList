package com.koror.app.entity;

public class Session extends AbstractEntity{

    private String userId;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
