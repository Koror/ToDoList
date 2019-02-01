package com.koror.app.entity;

import com.koror.app.enumerated.Priority;
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
@Table(name = "tm_task")
public class Task extends AbstractEntity implements Serializable {

    @Nullable
    @Basic
    private Priority priority;

    @Nullable
    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    private boolean complete;

    @Nullable
    @Basic
    @Column(nullable = false)
    private String creator;

    @Nullable
    @ManyToOne
    private Group group;

    @Nullable
    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssigneeTask> assigneeTaskList;

    public Task(@NotNull final String name) {
        this.name = name;
    }

    public Task(@NotNull final String name, @Nullable final Priority priority, @Nullable final Group group) {
        setName(name);
        this.priority = priority;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Name:" + getName() + " Complete: " + complete;
    }

}
