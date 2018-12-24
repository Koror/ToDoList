package com.koror.app.controller;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.enumeration.Priority;
import com.koror.app.service.GroupService;
import com.koror.app.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class CommandLineGUI implements InterfaceGUI {

    private final Scanner scanner = new Scanner(System.in);

    private final GroupService groupService;

    private final TaskService taskService;

    public CommandLineGUI(TaskService taskService, GroupService groupService)
    {
        this.taskService = taskService;
        this.groupService = groupService;
    }

    @Override
    public Group addGroup() {
        System.out.println("Input name group");
        return new Group(scanner.nextLine());
    }

    @Override
    public Task addTask() {
        System.out.println("Input task name and priority{LOW MEDIUM HIGH}");
        return new Task(scanner.nextLine(), Priority.valueOf(scanner.nextLine()));
    }

    @Override
    public Task completeTask(List<Task> taskList) {
        System.out.println(taskList);
        System.out.println("Input index task");
        Task task = taskList.get(Integer.parseInt(scanner.nextLine()));
        task.setComplete();
        return task;
    }

    public Task AddGroupToTask(List<Task> taskList, List<Group> groupList) {
        System.out.println(taskList);
        System.out.println("Input index task");
        Task task = taskList.get(Integer.parseInt(scanner.nextLine()));
        System.out.println(groupList);
        System.out.println("Input index group");
        Group group = groupList.get(Integer.parseInt(scanner.nextLine()));
        task.setGroupId(group.getId());
        return task;
    }

    @Override
    public String deleteTask(List<Task> taskList) {
        System.out.println(taskList);
        System.out.println("Input index task");
        return taskList.get(Integer.parseInt(scanner.nextLine())).getId();
    }

    @Override
    public String deleteGroup(List<Group> groupList) {
        System.out.println(groupList);
        System.out.println("Input index group");
        return groupList.get(Integer.parseInt(scanner.nextLine())).getId();
    }

    @Override
    public Group updateGroup(List<Group> groupList) {
        System.out.println("Input index group and name");
        Group group = groupList.get(Integer.parseInt(scanner.nextLine()));
        group.setName(scanner.nextLine());
        return group;
    }

    @Override
    public Task updateTask(List<Task> taskList) {
        System.out.println("index task, task name and priority{LOW MEDIUM HIGH}");
        Task task = taskList.get(Integer.parseInt(scanner.nextLine()));
        task.update(scanner.nextLine(), Priority.valueOf(scanner.nextLine()));
        return task;
    }

    public void readAllGroup() {
        int indexGroup = 0;
        List<Task> taskList = taskService.getTaskRepository().getTaskList();
        for (Group group : taskService.getGroupRepository().getGroupMap().values()) {
            System.out.println(indexGroup + " [" + group.toString() + "]");
            for (Task task : taskList) {
                if (task.getGroupId()!=null&&task.getGroupId().equals(group.getId())) {
                    System.out.println("  " + task.toString());
                }
            }
            indexGroup++;
        }
    }

    public void readAllTask() {
        int index = 0;
        for (Task task : taskService.getTaskRepository().getTaskMap().values()) {
            System.out.println("   " + index + " [" + task.toString() + "]");
            index++;
        }
    }

}
