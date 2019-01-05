package com.koror.app.command;

import java.io.IOException;

public class LoadDataJsonCommand extends AbstractCommand {
    @Override
    public void execute() throws IOException {
        bootstrap.getTaskService().loadDataJson();
        bootstrap.getGroupService().loadDataJson();
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
