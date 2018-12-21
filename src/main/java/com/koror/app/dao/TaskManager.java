package com.koror.app.dao;

import com.koror.app.GUI.GUICommandLine;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskManager implements Manager {

    private ArrayList<Group> groupList = new ArrayList<>();

    public ArrayList<Group> getGroupList() {
        return groupList;
    }

    GUICommandLine gui = new GUICommandLine();

    public void addGroup() {
        gui.addGroup();
        groupList.add(new Group(gui.getNameGroup()));
    }

    public void addTask() {
        gui.addTask();
        final Group group = groupList.get(gui.getIndexGroup());
        group.getTaskList().add(new Task(gui.getNameTask(), gui.getPriority()));
        groupList.set(gui.getIndexGroup(), group);
    }

    public void completeTask() {
        gui.completeTask();
        final Group group = groupList.get(gui.getIndexGroup());
        final Task task = group.getTaskList().get(gui.getIndexTask());
        task.complete();
        group.getTaskList().set(gui.getIndexTask(), task);
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
        final Group group = groupList.get(gui.getIndexGroup());
        group.getTaskList().remove(gui.getIndexTask());
    }

    public void deleteGroup() {
        gui.deleteGroup();
        groupList.remove(gui.getIndexGroup());
    }

    public void updateTask() {
        gui.updateTask();
        final Group group = groupList.get(gui.getIndexGroup());
        group.getTaskList().set(gui.getIndexTask(), new Task(gui.getNameTask(), gui.getPriority()));
        groupList.set(gui.getIndexGroup(), group);
    }

    @Override
    public void readAll(ArrayList<Group> list) {
        gui.readAll(list);
    }

}
