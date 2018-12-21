package com.koror.app.manager;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.Iterator;
import java.util.List;

public class TaskManager {

    public void addTask(List<Group> groupList, int indexGroup, String nameTask, String priority) {
        try {
            final Group group = groupList.get(indexGroup);
            group.getTaskList().add(new Task(nameTask, priority));
            groupList.set(indexGroup, group);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void completeTask(List<Group> groupList, int indexGroup, int indexTask) {
        try {
            final Group group = groupList.get(indexGroup);
            final Task task = group.getTaskList().get(indexTask);
            task.complete();
            group.getTaskList().set(indexTask, task);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void deleteTask(List<Group> groupList, int indexGroup, int indexTask) {
        try {
            final Group group = groupList.get(indexGroup);
            group.getTaskList().remove(indexTask);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void updateTask(List<Group> groupList, int indexGroup, int indexTask, String nameTask, String priority) {
        try {
            final Group group = groupList.get(indexGroup);
            group.getTaskList().set(indexTask, new Task(nameTask, priority));
            groupList.set(indexGroup, group);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public void clearTask(List<Group> groupList) {
        for (Group group : groupList) {
            Iterator<Task> taskIterator = group.getTaskList().iterator();
            while (taskIterator.hasNext()) {
                if (taskIterator.next().isComplete())
                    taskIterator.remove();
            }
        }
    }

}
