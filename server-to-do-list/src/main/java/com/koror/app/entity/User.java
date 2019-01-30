package com.koror.app.entity;

import com.koror.app.enumerated.Access;
import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tm_user")
public class User extends AbstractEntity implements Serializable {

    @NotNull
    @Basic
    @Column(nullable = false)
    private String login;

    @NotNull
    @Basic
    @Column(nullable = false)
    private String password;

    @Nullable
    @Basic
    private String name;

    @Nullable
    @Basic
    private String email;

    @Nullable
    @Basic
    private Access access = Access.USER_ACCESS;

    public User(@NotNull String login,@NotNull String password) {
        this.login = login;
        this.password = Hash.createHashString(password);
    }

    @Override
    public String toString() {
        return login;
    }
}
