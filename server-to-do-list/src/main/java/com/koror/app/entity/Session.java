package com.koror.app.entity;

import com.koror.app.util.AppConfig;
import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tm_session")
public class Session extends AbstractEntity {

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    @Nullable
    @Column(nullable = false)
    private String signature;

    @Nullable
    private String ip;

    public Session(@NotNull String id,@NotNull User user,@NotNull String signature, @Nullable String ip) {
        this.id = id;
        this.user = user;
        this.signature = signature;
        this.ip = ip;
    }

    public void hashSignature() {
            this.signature = Hash.createHashString(id + user.getId() + ip);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(signature, session.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signature);
    }
}
