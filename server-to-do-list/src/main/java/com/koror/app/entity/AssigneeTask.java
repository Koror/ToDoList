package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tm_assigneetask")
public class AssigneeTask extends AbstractEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    public AssigneeTask(){

    }

    public AssigneeTask(User user, Task task) {
        this.user = user;
        this.task = task;
    }

}
