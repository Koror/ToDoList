package com.koror.app.service;

import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();

    private final GroupRepository groupRepository = new GroupRepository(taskRepository);

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

}
