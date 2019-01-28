package com.koror.app.entity;

import com.koror.app.enumerated.Access;
import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
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

    @Basic
    private String name;

    @Basic
    private String email;

    @Basic
    private Access access = Access.USER_ACCESS;

    public User() {

    }

    public User(@NotNull String login,@NotNull String password) {
        this.login = login;
        this.password = Hash.createHashString(password);
    }

    @Override
    public String toString() {
        return login;
    }
}
