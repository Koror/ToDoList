package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tm_group")
public class Group extends AbstractEntity implements Serializable {

    @NotNull
    @Basic
    @Column(nullable = false)
    private String creator;

    @NotNull
    @Basic
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    public Group(){

    }

    public Group(@NotNull final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName();
    }

}
