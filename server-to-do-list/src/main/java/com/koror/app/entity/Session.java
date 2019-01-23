package com.koror.app.entity;

import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tm_session")
public class Session extends AbstractEntity {

    @Setter
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

    public Session() {
        hashSignature();
    }

    public Session(String id, String userId, String signature, String ip) {
        this.id = id;
        this.userId = userId;
        this.signature = signature;
        this.ip = ip;
    }

    public void hashSignature() {
        this.signature = Hash.createHashString(getId() + userId);
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
