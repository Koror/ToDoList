package com.koror.app.entity;

import java.util.UUID;

public class AssigneeGroup {

    private String id = UUID.randomUUID().toString();

    private final String userId;

    private final String groupId;

    public AssigneeGroup(String userId, String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

}
