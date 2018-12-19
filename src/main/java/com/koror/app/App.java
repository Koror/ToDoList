package com.koror.app;

public class App 
{
    public static void main( String[] args )
    {
        Manager manager = new Manager();
        manager.addGroup("DayTask");
        manager.groupList.get(0).addTask("Shop",Priority.MIDDLE);
        manager.groupList.get(0).completeTask(0);
        System.out.println( manager.groupList.get(0).isCompleteTask(0));
    }
}
