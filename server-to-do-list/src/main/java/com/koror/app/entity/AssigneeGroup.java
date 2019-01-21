package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

public class AssigneeGroup extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    private String userId;

    @Setter
    @Getter
    private String groupId;

    public AssigneeGroup(){

    }

    public AssigneeGroup(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

}
