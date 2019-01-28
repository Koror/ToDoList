package com.koror.app.entity;

import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "tm_session")
public class Session extends AbstractEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @NotNull
    @Basic
    @Column(nullable = false)
    private String signature;

    @Basic
    private String ip;

    public Session() {
        hashSignature();
    }

    public Session(@NotNull String id,@NotNull User user,@NotNull String signature, String ip) {
        this.id = id;
        this.user = user;
        this.signature = signature;
        this.ip = ip;
    }

    public void hashSignature() {
        this.signature = Hash.createHashString(getId() + user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(user.getId(), session.user.getId()) &&
                Objects.equals(signature, session.signature) &&
                Objects.equals(ip, session.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.id, signature, ip);
    }
}
