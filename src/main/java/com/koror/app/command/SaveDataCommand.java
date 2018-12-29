package com.koror.app.command;

public class SaveDataCommand extends AbstractCommand {

    @Override
    public void execute() {
        bootstrap.getTaskService().saveData();
        bootstrap.getGroupService().saveData();
        System.out.println("Save complete");
    }

    @Override
    public String command() {
        return "Save";
    }

    @Override
    public String description() {
        return "Save data to file";
    }
}
