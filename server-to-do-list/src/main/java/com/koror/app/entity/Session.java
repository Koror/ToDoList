package com.koror.app.entity;

import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "tm_session")
public class Session extends AbstractEntity {

    @Nullable
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Nullable
    @Column(nullable = false)
    private String signature;

    @Nullable
    private String ip;

    public Session(@NotNull String id, @NotNull User user, @NotNull String signature, @Nullable String ip) {
        this.id = id;
        this.user = user;
        this.signature = signature;
        this.ip = ip;
    }

    public void hashSignature() {
        this.signature = Hash.createHashString(id + user.getId() + ip);
    }

}
