package com.koror.app.command.datacommand;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class SaveDataXmlCommand extends AbstractCommand {
    @Override
    public void execute() throws IOException {
        bootstrap.getTaskService().saveDataXml();
        bootstrap.getGroupService().saveDataXml();
        System.out.println("Save complete");
    }

    @Override
    public String command() {
        return "SaveInXml";
    }

    @Override
    public String description() {
        return "Save data in xml file";
    }
}
