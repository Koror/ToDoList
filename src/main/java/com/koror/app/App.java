package com.koror.app;

import com.koror.app.DAO.Manager;
import com.koror.app.Entity.Group;
import com.koror.app.Entity.Task;
import com.koror.app.Util.Priority;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        start();
    }

    public static void start()
    {
        Scanner in = new Scanner(System.in);
        Manager manager = new Manager();
        while(true) {
            System.out.println("Action: AddGroup ReadAll AddTask CompleteTask");
            String action;
            action = in.nextLine();
            switch (action) {

                case "AddGroup":
                    System.out.println("Input name group");
                    manager.addGroup(in.nextLine());
                    break;
                case "ReadAll":
                    for (Group group : manager.getGroupList()) {
                        System.out.println(group.toString());
                        for (Task task : group.getTaskList()) {
                            System.out.println(task.toString());
                        }
                    }
                    break;
                case "AddTask":
                    System.out.println("Input index group | task name | priority{LOW MEDIUM HIGH}");
                    int indexGroupT = in.nextInt();
                    String name = in.next();
                    String priority = in.next();
                    manager.addTask(indexGroupT, name, priority);
                    break;

                case "CompleteTask":
                    System.out.println("Input index group and task");
                    int indexGroupC = in.nextInt();
                    int indexTaskC = in.nextInt();
                    manager.completeTask(indexGroupC, indexTaskC);
                    break;
                case "Test":
                    System.out.println(manager.getGroupList().get(0));
                case "Exit":
                    return ;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }
}
