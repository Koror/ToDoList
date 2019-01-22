package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tm_assigneegroup")
public class AssigneeGroup extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Setter
    @Getter
    @Basic
    private String userId;

    @Setter
    @Getter
    @Basic
    private String groupId;

    public AssigneeGroup(){

    }

    public AssigneeGroup(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

}
