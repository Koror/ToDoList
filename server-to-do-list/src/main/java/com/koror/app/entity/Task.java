package com.koror.app.entity;

import com.koror.app.enumerated.Priority;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tm_task")
public class Task extends AbstractEntity implements Serializable {

    @Basic
    private Priority priority;

    @NotNull
    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    private boolean complete;

    @NotNull
    @Basic
    @Column(nullable = false)
    private String creator;

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;

    public Task() {

    }

    public Task(@NotNull final String name) {
        this.name = name;
    }

    public Task(@NotNull final String name, final Priority priority, final Group group) {
        setName(name);
        this.priority = priority;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Name:" + getName() + " Complete: " + complete;
    }

}
