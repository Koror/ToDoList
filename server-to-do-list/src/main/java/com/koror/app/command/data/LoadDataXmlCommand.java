package com.koror.app.command.data;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class LoadDataXmlCommand extends AbstractCommand {
    @Override
    public void execute() throws IOException {
        System.out.println("Loading...");
        bootstrap.getTaskService().loadDataXml();
        bootstrap.getGroupService().loadDataXml();
        bootstrap.getUserService().loadDataXml();
        bootstrap.getAssigneeTaskService().loadDataXml();
        bootstrap.getAssigneeGroupService().loadDataXml();
        System.out.println("Load complete");
    }

    @Override
    public String command() {
        return "LoadFromXml";
    }

    @Override
    public String description() {
        return "Load data from xml file";
    }
}
