package com.koror.app.entity;

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

    @Nullable
    @ManyToOne
    private User user;

    public Group(@NotNull final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName();
    }

}
