package com.koror.app.service;

import com.koror.app.entity.Group;
import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

import java.util.List;
import java.util.Map;

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

    public void addGroup(Group group) {
        groupRepository.addGroup(group);
    }

    public void updateGroup(Group group) {
        groupRepository.updateGroup(group);
    }

    public void deleteGroup(String id) {
        groupRepository.deleteGroup(id);
    }

    public Map<String, Group> getGroupMap() {
        return groupRepository.getGroupMap();
    }

    public List<Group> getGroupList() {
        return groupRepository.getGroupList();
    }

}
