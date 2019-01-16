package com.koror.app.command.help;

import com.koror.app.command.AbstractCommand;

import java.util.Map;

public class HelpUserCommand extends AbstractCommand {

    @Override
    public void execute() {
        Map<String, AbstractCommand> commandMap = bootstrap.getServerCommands();
        for (AbstractCommand command : commandMap.values())
            System.out.println(command.command() + " : " + command.description());
    }

    @Override
    public String command() {
        return "Help";
    }

    @Override
    public String description() {
        return "List of commands";
    }
}
