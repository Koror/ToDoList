package com.koror.app.entity;

import java.io.Serializable;
import java.util.UUID;

public class AssigneeGroup extends AbstractEntity implements Serializable {

    private String userId;

    private String groupId;

    public AssigneeGroup(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

}
