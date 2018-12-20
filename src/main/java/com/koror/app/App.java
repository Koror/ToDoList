package com.koror.app;

import com.koror.app.DAO.Manager;

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
            System.out.println("Action: AddGroup ReadAll AddTask CompleteTask Clear Exit");
            String action;
            action = in.nextLine();
            switch (action) {

                case "AddGroup":
                    System.out.println("Input name group");
                    manager.addGroup(in.nextLine());
                    break;
                case "ReadAll":
                   manager.readAll();
                    break;
                case "AddTask":
                    System.out.println("Input index group | task name | priority{LOW MEDIUM HIGH}");
                    int indexGroupT = in.nextInt();
                    String name = in.next();
                    String priority = in.nextLine();
                    manager.addTask(indexGroupT, name, priority);
                    break;

                case "CompleteTask":
                    System.out.println("Input index group and task");
                    int indexGroupC = in.nextInt();
                    int indexTaskC = in.nextInt();
                    in.nextLine();
                    manager.completeTask(indexGroupC, indexTaskC);
                    break;
                case "Clear":
                    manager.clearTask();
                    break;
                case "Test":
                    manager.addGroup("Day");
                    manager.addGroup("Week");
                    manager.addTask(0,"Shop","MEDIUM");
                    manager.addTask(0,"Read","MEDIUM");
                    manager.addTask(1,"Work","HIGH");
                    manager.completeTask(0,1);
                    manager.completeTask(1,0);

                    break;
                case "Exit":
                    return ;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }
}
