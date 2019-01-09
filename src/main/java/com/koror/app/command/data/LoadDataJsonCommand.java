package com.koror.app.command.data;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class LoadDataJsonCommand extends AbstractCommand {
    @Override
    public void execute() throws IOException {
        System.out.println("Loading...");
        bootstrap.getTaskService().loadDataJson();
        bootstrap.getGroupService().loadDataJson();
        bootstrap.getUserService().loadDataJson();
        bootstrap.getAssigneeTaskService().loadDataJson();
        bootstrap.getAssigneeGroupService().loadDataJson();
        System.out.println("Load complete");
    }

    @Override
    public String command() {
        return "LoadFromJson";
    }

    @Override
    public String description() {
        return "Load data from json file";
    }
}
