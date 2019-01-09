package com.koror.app.command.data;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class SaveDataSerializationCommand extends AbstractCommand {

    @Override
    public void execute() throws IOException {
        System.out.println("Saving...");
        bootstrap.getTaskService().saveDataSerialization();
        bootstrap.getGroupService().saveDataSerialization();
        bootstrap.getUserService().saveDataSerialization();
        bootstrap.getAssigneeTaskService().saveDataSerialization();
        bootstrap.getAssigneeGroupService().saveDataSerialization();
        System.out.println("Save complete");
    }

    @Override
    public String command() {
        return "SaveInBin";
    }

    @Override
    public String description() {
        return "Save data to bin file";
    }
}
