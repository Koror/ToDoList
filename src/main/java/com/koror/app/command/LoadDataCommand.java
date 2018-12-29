package com.koror.app.command;

public class LoadDataCommand extends AbstractCommand {

    @Override
    public void execute() {
        bootstrap.getTaskService().loadData();
        bootstrap.getGroupService().loadData();
        System.out.println("Load complete");
    }

    @Override
    public String command() {
        return "Load";
    }

    @Override
    public String description() {
        return "Load data from file";
    }
}
