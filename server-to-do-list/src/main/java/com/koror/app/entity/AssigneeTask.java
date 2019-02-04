package com.koror.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tm_assigneetask")
public class AssigneeTask extends AbstractEntity implements Serializable {

    @Nullable
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Nullable
    @ManyToOne
    @JoinColumn(nullable = false)
    private Task task;

    public AssigneeTask(@NotNull User user, @NotNull Task task) {
        this.user = user;
        this.task = task;
    }

}
