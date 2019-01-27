package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tm_group")
public class Group extends AbstractEntity implements Serializable {

    @Basic
    private String creator;

    @Basic
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    public Group(){

    }

    public Group(final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName();
    }

}
