package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public abstract class AbstractCommand {

    protected final Bootstrap bootstrap;

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract void execute();

    public abstract String command();

    public abstract void description();
}
