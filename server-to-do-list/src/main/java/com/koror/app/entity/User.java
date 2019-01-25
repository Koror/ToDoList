package com.koror.app.entity;

import com.koror.app.enumerated.Access;
import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tm_user")
public class User extends AbstractEntity implements Serializable {

    @Basic
    @Column(nullable = false)
    private String login;

    @Basic
    @Column(nullable = false)
    private String password;

    @Basic
    private String name;

    @Basic
    private String email;

    @Basic
    private Access access = Access.USER_ACCESS;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Session> sessionList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Group> groupList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Task> taskList;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = Hash.createHashString(password);
    }

    @Override
    public String toString() {
        return login;
    }
}
