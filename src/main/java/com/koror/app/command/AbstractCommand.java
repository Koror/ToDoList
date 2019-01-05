package com.koror.app.command;

import com.koror.app.controller.Bootstrap;

import java.io.IOException;

public abstract class AbstractCommand {

    protected Bootstrap bootstrap;

    public abstract void execute() throws IOException, ClassNotFoundException;

    public abstract String command();

    public abstract String description();

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

}
