package com.koror.app.entity;

import com.koror.app.enumerated.Access;
import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tm_user")
public class User extends AbstractEntity implements Serializable {

    @Nullable
    @Column(nullable = false)
    private String login;

    @Nullable
    @Column(nullable = false)
    private String password;

    @Nullable
    private String name;

    @Nullable
    private String email;

    @Nullable
    private Access access = Access.USER_ACCESS;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Session> sessionList;

    public User(@NotNull String login, @NotNull String password) {
        this.login = login;
        this.password = Hash.createHashString(password);
    }

    @Override
    public String toString() {
        return login;
    }
}
