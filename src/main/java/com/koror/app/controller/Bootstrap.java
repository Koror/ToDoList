package com.koror.app.controller;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.TaskEndpoint;
import com.koror.app.endpoint.UserEndpoint;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.*;
import com.koror.app.service.*;

import javax.xml.ws.Endpoint;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Bootstrap implements IBootstrap {

    private final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();

    private final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);

    private final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();

    private final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);

    private final GroupRepository groupRepository = new GroupRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);

    private final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);

    private final UserRepository userRepository = new UserRepository();

    private final UserService userService = new UserService(userRepository);

    private final SessionRepository sessionRepository = new SessionRepository();

    private final SessionService sessionService = new SessionService(sessionRepository);

    private final Map<String, AbstractCommand> commandUserMap = new HashMap<>();

    private final Map<String, AbstractCommand> commandLoginMap = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private void loadDataFromJson() throws IOException {
        try {
            getTaskService().loadDataJson();
            getGroupService().loadDataJson();
            getUserService().loadDataJson();
            getAssigneeTaskService().loadDataJson();
            getAssigneeGroupService().loadDataJson();
        }catch (FileNotFoundException e){
            System.out.println("Data is empty");
        }
    }

    public void startServer()throws IOException{
        loadDataFromJson();
        Endpoint.publish("http://localhost:8080/TaskEndpoint?WSDL", new TaskEndpoint(this));
        Endpoint.publish("http://localhost:8080/UserEndpoint?WSDL", new UserEndpoint(this));
    }

    @Override
    public GroupService getGroupService() {
        return groupService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public AssigneeGroupService getAssigneeGroupService() {
        return assigneeGroupService;
    }

    @Override
    public AssigneeTaskService getAssigneeTaskService() {
        return assigneeTaskService;
    }

    @Override
    public Map<String, AbstractCommand> getCommandUserMap() {
        return commandUserMap;
    }

    @Override
    public Map<String, AbstractCommand> getCommandLoginMap() {
        return commandLoginMap;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    @Override
    public Integer nextInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new WrongInputException("Wrong input", e);
        }
    }

    @Override
    public String nextLine() {
        final String input = scanner.nextLine();
        if (input.equals("")) throw new WrongInputException("Wrong input");
        return input;
    }

}
