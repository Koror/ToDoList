package com.koror.app.service;

import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

public class TaskService {

    private final TaskRepository taskRepository;

    private final GroupRepository groupRepository;

    public TaskService(TaskRepository taskRepository, GroupRepository groupRepository) {
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
