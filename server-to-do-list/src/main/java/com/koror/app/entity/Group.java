package com.koror.app.entity;

import com.koror.app.dto.GroupDTO;
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

    @Nullable
    @Column(nullable = false)
    private String creator;

    @Nullable
    @Column(nullable = false)
    private String name;

    @Nullable
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AssigneeGroup> assigneeGroupList;

    public Group(@NotNull final String name) {
        this.name = name;
    }

    public Group(GroupDTO groupDTO, User user){
        id = groupDTO.getId();
        creator = groupDTO.getCreator();
        name = groupDTO.getName();
        this.user = user;
    }

    @Override
    public String toString() {
        return getName();
    }

}
