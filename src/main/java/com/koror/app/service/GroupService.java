package com.koror.app.service;

import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

public class GroupService {

    private final TaskRepository taskRepository;

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.groupRepository = groupRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

}
