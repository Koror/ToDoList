package com.koror.app.entity;

import java.io.Serializable;

public class AssigneeTask extends AbstractEntity implements Serializable {

    private String userId;

    private String taskId;

    public AssigneeTask(){

    }

    public AssigneeTask(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
