package com.koror.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tm_assigneetask")
public class AssigneeTask extends AbstractEntity implements Serializable {

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Task task;

    public AssigneeTask(@NotNull User user,@NotNull Task task) {
        this.user = user;
        this.task = task;
    }

}
