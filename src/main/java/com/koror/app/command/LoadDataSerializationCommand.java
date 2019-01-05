package com.koror.app.command;

import java.io.IOException;

public class LoadDataSerializationCommand extends AbstractCommand {

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        bootstrap.getTaskService().loadDataSerialization();
        bootstrap.getGroupService().loadDataSerialization();
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
