package com.koror.app.dao;

import com.koror.app.GUI.GUICommandLine;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager extends Manager {

    private ArrayList<Group> groupList = new ArrayList<>();

    public ArrayList<Group> getGroupList() {
        return groupList;
    }

    GUICommandLine gui = new GUICommandLine();

    public void addGroup() {
        gui.addGroup();
        groupList.add(new Group(gui.nameGroup));
    }

    public void addTask() {
        gui.addTask();
        final Group group = groupList.get(gui.indexGroup);
        group.getTaskList().add(new Task(gui.nameTask, gui.priority));
        groupList.set(gui.indexGroup, group);
    }

    public void completeTask() {
        gui.completeTask();
        final Group group = groupList.get(gui.indexGroup);
        final Task task = group.getTaskList().get(gui.indexTask);
        task.complete();
        group.getTaskList().set(gui.indexTask, task);
    }

    public void clear() {
        for (Group group : getGroupList()) {
            Iterator<Task> taskIterator = group.getTaskList().iterator();
            while (taskIterator.hasNext()) {
                if (taskIterator.next().isComplete())
                    taskIterator.remove();
            }
        }
    }

    public void deleteTask() {
        gui.deleteTask();
        final Group group = groupList.get(gui.indexGroup);
        group.getTaskList().remove(gui.indexTask);
    }

    public void deleteGroup() {
        gui.deleteGroup();
        groupList.remove(gui.indexGroup);
    }

    public void readAll() {
        gui.readAll(getGroupList());
    }

}
