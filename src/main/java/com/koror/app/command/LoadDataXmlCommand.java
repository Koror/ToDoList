package com.koror.app.command;

import java.io.IOException;

public class LoadDataXmlCommand extends AbstractCommand {
    @Override
    public void execute() throws IOException {
        bootstrap.getTaskService().loadDataXml();
        bootstrap.getGroupService().loadDataXml();
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
