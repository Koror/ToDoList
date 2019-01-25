package com.koror.app.entity;

import com.koror.app.enumerated.Priority;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tm_task")
public class Task extends AbstractEntity implements Serializable {

    @Basic
    private Priority priority;

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    private boolean complete;

    @Basic
    private String creator;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Task() {

    }

    public Task(final String name) {
        this.name = name;
    }

    public Task(final String name, final Priority priority, final Group group) {
        setName(name);
        this.priority = priority;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Name:" + getName() + " Complete: " + complete;
    }

}
