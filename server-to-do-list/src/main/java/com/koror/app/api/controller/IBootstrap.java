package com.koror.app.api.controller;

import com.koror.app.command.AbstractCommand;

import java.util.Map;

public interface IBootstrap {

    void startServer() throws ReflectiveOperationException;

    Map<String, AbstractCommand> getServerCommands();

    Integer nextInt();

    String nextLine();

}
