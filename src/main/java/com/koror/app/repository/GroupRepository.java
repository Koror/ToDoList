package com.koror.app.repository;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepository {

    private final Map<String, Group> groupMap = new HashMap<>();

    TaskRepository taskRepository;

    public GroupRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Map<String, Group> getGroupMap() {
        return groupMap;
    }

    public void addGroup(Group group) {
        groupMap.put(group.getId(), group);
    }

    public void updateGroup(Group group) {
        groupMap.put(group.getId(), group);
    }

    public void deleteGroup(String id) {
        try {
            groupMap.remove(id);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void readAllGroup() {
        int indexGroup = 0;
        List<Task> taskList = taskRepository.getTaskList();
        for (Group group : groupMap.values()) {
            System.out.println(indexGroup + " [" + group.toString() + "]");
            for (Task task : taskList) {
                if (task.getGroupId().equals(group.getId())) {
                    System.out.println("  " + task.toString());
                }
            }
            indexGroup++;
        }
    }

    public List<Group> getGroupList() {
        return new ArrayList<>(groupMap.values());
    }

}
