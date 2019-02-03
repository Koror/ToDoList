package com.koror.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tm_assigneegroup")
public class AssigneeGroup extends AbstractEntity implements Serializable {

    @Nullable
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Nullable
    @ManyToOne
    @JoinColumn(nullable = false)
    private Group group;

    public AssigneeGroup(@NotNull User user,@NotNull Group group) {
        this.user = user;
        this.group = group;
    }

}
