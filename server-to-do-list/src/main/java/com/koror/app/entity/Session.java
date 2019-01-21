package com.koror.app.entity;

import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;

import java.net.InetAddress;
import java.util.Objects;

public class Session extends AbstractEntity{

    @Getter
    private String userId;

    @Setter
    @Getter
    private String signature;

    @Setter
    @Getter
    private String ip;

    public Session(){

    }

    public Session(String userId){
        this.userId = userId;
        this.signature = Hash.getHashString(getId() + userId);
    }

    public Session(String userId, String ip){
        this.userId = userId;
        this.signature = Hash.getHashString(getId() + userId);
        this.ip = ip;
    }

    public void setUserId(String userId){
        this.userId = userId;
        this.signature = Hash.getHashString(getId() + userId);
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
