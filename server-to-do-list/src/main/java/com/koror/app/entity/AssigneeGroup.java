package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tm_assigneegroup")
public class AssigneeGroup extends AbstractEntity implements Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;

    public AssigneeGroup(){

    }

    public AssigneeGroup(User user, Group group) {
        this.user = user;
        this.group = group;
    }

}
