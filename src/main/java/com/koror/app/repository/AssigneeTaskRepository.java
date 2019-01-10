package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssigneeTaskRepository implements IAssigneeTaskRepository {

    Map<String, AssigneeTask> assigneeTaskMap = new HashMap<>();

    @Override
    public void addAssignee(AssigneeTask assigneeTask) {
        assigneeTaskMap.put(assigneeTask.getId(), assigneeTask);
    }

    @Override
    public void deleteAssignee(String id) {
        assigneeTaskMap.remove(id);
    }

    @Override
    public void deleteAssigneeByParam(String userId, String taskId) {
        for (AssigneeTask assigneeTask : assigneeTaskMap.values()) {
            if (userId.equals(assigneeTask.getUserId()) && taskId.equals(assigneeTask.getTaskId()))
                assigneeTaskMap.remove(assigneeTask.getId());
        }
    }

    @Override
    public AssigneeTask getAssigneeById(String id) {
        return assigneeTaskMap.get(id);
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        for (AssigneeTask assigneeTask : assigneeTaskMap.values()) {
            if (assigneeTask.getUserId().equals(userId))
                return assigneeTask;
        }
        return null;
    }

    @Override
    public List<AssigneeTask> getAssigneeTaskList() {
        return new ArrayList<>(assigneeTaskMap.values());
    }

}
