package com.koror.app.command.data;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class LoadDataSerializationCommand extends AbstractCommand {

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        System.out.println("Loading...");
        bootstrap.getTaskService().loadDataSerialization();
        bootstrap.getGroupService().loadDataSerialization();
        bootstrap.getUserService().loadDataSerialization();
        bootstrap.getAssigneeTaskService().loadDataSerialization();
        bootstrap.getAssigneeGroupService().loadDataSerialization();
        System.out.println("Load complete");
    }

    @Override
    public String command() {
        return "LoadFromBin";
    }

    @Override
    public String description() {
        return "Load data from bin file";
    }
}
