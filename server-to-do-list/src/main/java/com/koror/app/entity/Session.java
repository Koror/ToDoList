package com.koror.app.entity;

import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.net.InetAddress;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tm_session")
public class Session extends AbstractEntity{

    @Setter
    @Getter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Getter
    @Basic
    private String userId;

    @Setter
    @Getter
    @Basic
    private String signature;

    @Setter
    @Getter
    @Basic
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
        return Objects.equals(userId, session.userId) &&
                Objects.equals(signature, session.signature) &&
                Objects.equals(ip, session.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, signature, ip);
    }
}
