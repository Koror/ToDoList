package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AssigneeTask extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    private String userId;

    @Setter
    @Getter
    private String taskId;

    public AssigneeTask(){

    }

    public AssigneeTask(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

}
