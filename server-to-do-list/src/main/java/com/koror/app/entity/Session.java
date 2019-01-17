package com.koror.app.entity;

import com.koror.app.util.Hash;

import java.util.Objects;

public class Session extends AbstractEntity{

    private String userId;

    private String signature;

    public Session(){

    }

    public Session(String userId){
        this.userId = userId;
        this.signature = Hash.getHashString(getId() + userId);
    }
    public void setUserId(String userId){
        this.userId = userId;
        this.signature = Hash.getHashString(getId() + userId);
    }

    public String getUserId() {
        return userId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(getId(), session.getId()) &&
                Objects.equals(userId, session.userId) &&
                Objects.equals(signature, session.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), userId, signature);
    }
}
