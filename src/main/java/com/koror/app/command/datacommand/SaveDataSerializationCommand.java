package com.koror.app.command.datacommand;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class SaveDataSerializationCommand extends AbstractCommand {

    @Override
    public void execute() throws IOException {
        bootstrap.getTaskService().saveDataSerialization();
        bootstrap.getGroupService().saveDataSerialization();
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
