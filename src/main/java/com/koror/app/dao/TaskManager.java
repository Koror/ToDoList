package com.koror.app.dao;

import com.koror.app.gui.CommandLineGUI;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskManager implements Manager {

    private List<Group> groupList = new ArrayList<>();

    public List<Group> getGroupList() {
        return groupList;
    }

    CommandLineGUI gui = new CommandLineGUI();

    public void addGroup() {
        gui.addGroup();
        groupList.add(new Group(gui.getNameGroup()));
    }

    public void addTask() {
        gui.addTask();
        try {
            final Group group = groupList.get(gui.getIndexGroup());
            group.getTaskList().add(new Task(gui.getNameTask(), gui.getPriority()));
            groupList.set(gui.getIndexGroup(), group);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void completeTask() {
        gui.completeTask();
        try {
            final Group group = groupList.get(gui.getIndexGroup());
            final Task task = group.getTaskList().get(gui.getIndexTask());
            task.complete();
            group.getTaskList().set(gui.getIndexTask(), task);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
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
        try {
            final Group group = groupList.get(gui.getIndexGroup());
            group.getTaskList().remove(gui.getIndexTask());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void deleteGroup() {
        gui.deleteGroup();
        try {
            groupList.remove(gui.getIndexGroup());
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void updateTask() {
        gui.updateTask();
        try {
            final Group group = groupList.get(gui.getIndexGroup());
            group.getTaskList().set(gui.getIndexTask(), new Task(gui.getNameTask(), gui.getPriority()));
            groupList.set(gui.getIndexGroup(), group);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    @Override
    public void readAll(List<Group> list) {
        gui.readAll(list);
    }

}
