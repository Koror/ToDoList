package com.koror.app.service;

import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

public class GroupService {

    private final TaskRepository taskRepository = new TaskRepository();

    private final GroupRepository groupRepository = new GroupRepository(taskRepository);

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

}
