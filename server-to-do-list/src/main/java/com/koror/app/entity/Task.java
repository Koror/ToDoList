package com.koror.app.entity;

import com.koror.app.enumerated.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tm_task")
public class Task extends AbstractEntity implements Serializable {

    @Nullable
    private Priority priority;

    @Nullable
    @Column(nullable = false)
    private String name;

    @Nullable
    @Column(nullable = false)
    private String creator;

    @Nullable
    private boolean complete;

    @Nullable
    @ManyToOne
    private Group group;

    @Nullable
    @ManyToOne
    private User user;

    private Date dateBegin;

    private Date dateEnd;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
        return getName();
    }

}
