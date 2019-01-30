package com.koror.app.api.controller;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Session;
import com.koror.app.service.*;

import java.io.IOException;
import java.util.Map;

public interface IBootstrap {

    void startServer() throws ReflectiveOperationException;

    Map<String, AbstractCommand> getServerCommands();

    Integer nextInt();

    String nextLine();

}
