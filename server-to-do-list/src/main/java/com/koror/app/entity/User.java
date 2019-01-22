package com.koror.app.entity;

import com.koror.app.enumerated.Access;
import com.koror.app.util.Hash;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tm_user")
public class User extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Setter
    @Getter
    @Basic
    private String login;

    @Setter
    @Getter
    @Basic
    private String password;

    @Setter
    @Getter
    @Basic
    private String name;

    @Setter
    @Getter
    @Basic
    private String email;

    @Setter
    @Getter
    @Basic
    private Access access = Access.USER_ACCESS;

    public User(){

    }

    public User(String login, String password) {
        this.login = login;
        this.password = Hash.getHashString(password);;
    }

    @Override
    public String toString() {
        return login;
    }
}
