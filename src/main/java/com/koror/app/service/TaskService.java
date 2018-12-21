package com.koror.app.service;

import com.koror.app.entity.Group;
import com.koror.app.manager.TaskManager;

import java.util.List;

import static com.koror.app.service.ToDoListService.gui;

public class TaskService {

    private final TaskManager taskManager = new TaskManager();

    private final List<Group> groupList;

    public TaskService(List<Group> groupList) {
        this.groupList = groupList;
    }

    public void addTask() {
        gui.addTask();
        taskManager.addTask(groupList, gui.getIndexGroup(), gui.getNameTask(), gui.getPriority());
    }

    public void readAll() {
        gui.readAll(groupList);
    }

    public void updateTask() {
        gui.updateTask();
        taskManager.updateTask(groupList, gui.getIndexGroup(), gui.getIndexTask(), gui.getNameTask(), gui.getPriority());
    }

    public void deleteTask() {
        gui.deleteTask();
        taskManager.deleteTask(groupList, gui.getIndexGroup(), gui.getIndexTask());
    }

    public void completeTask() {
        gui.completeTask();
        taskManager.completeTask(groupList, gui.getIndexGroup(), gui.getIndexTask());
    }

    public void clearTask() {
        taskManager.clearTask(groupList);
    }

}
