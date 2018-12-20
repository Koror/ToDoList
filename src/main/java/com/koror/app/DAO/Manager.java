package com.koror.app.DAO;

import com.koror.app.Entity.Group;
import com.koror.app.Entity.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class Manager {
    private ArrayList<Group> groupList = new ArrayList<>();

    public ArrayList<Group> getGroupList()
    {
        return groupList;
    }
    public void addGroup(String name)
    {
        Group group = new Group(name);
        groupList.add(group);
    }

    public void addTask(int indexGroup, String text, String priority)
    {

        Task task = new Task(text, priority);
        Group group = groupList.get(indexGroup);
        group.getTaskList().add(task);
        groupList.set(indexGroup,group);
    }

    public void completeTask(int indexGroup, int indexTask)
    {
        Group group = groupList.get(indexGroup);
        Task task = group.getTaskList().get(indexTask);
        task.complete();
        group.getTaskList().set(indexTask,task);
    }

    public void clearTask()
    {
        Iterator<Group> groupIterator = getGroupList().iterator();
        while(groupIterator.hasNext()) {
            Iterator<Task> taskIterator = groupIterator.next().getTaskList().iterator();
            while (taskIterator.hasNext()) {
                if (taskIterator.next().isComplete())
                   taskIterator.remove();
            }
        }
    }

    public void readAll()
    {
        int iGroup=0;
        int iTask;
        for (Group group :getGroupList()) {
            iTask=0;
            System.out.println(iGroup+" ["+group.toString()+"]");
            for (Task task : group.getTaskList()) {
                System.out.println("   "+iTask+" ["+task.toString()+"]");
                iTask++;
            }
            iGroup++;
        }
    }

}
