package com.koror.app.command.datacommand;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class SaveDataJsonCommand extends AbstractCommand {
    @Override
    public void execute() throws IOException {
        bootstrap.getTaskService().saveDataJson();
        bootstrap.getGroupService().saveDataJson();
        System.out.println("Save complete");
    }

    @Override
    public String command() {
        return "SaveInJson";
    }

    @Override
    public String description() {
        return "Save data in json file";
    }
}
