package com.koror.app;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.controller.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws ReflectiveOperationException {
        SeContainerInitializer.newInstance().addPackages(App.class).initialize().select(IBootstrap.class).get().startServer();
    }

}
