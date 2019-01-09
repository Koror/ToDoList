package com.koror.app.entity;

import java.util.UUID;

public class AssigneeTask {

    private final String id = UUID.randomUUID().toString();

    private final String userId;

    private final String taskId;

    public AssigneeTask(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTaskId() {
        return taskId;
    }

}
