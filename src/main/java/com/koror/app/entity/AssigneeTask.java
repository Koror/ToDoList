package com.koror.app.entity;

import java.io.Serializable;
import java.util.UUID;

public class AssigneeTask implements Serializable {

    private final String id = UUID.randomUUID().toString();

    private String userId;

    private String taskId;

    public AssigneeTask(){

    }

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
