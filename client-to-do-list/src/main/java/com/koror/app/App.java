package com.koror.app;

import com.koror.app.controller.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public class App {
    public static void main(String[] args) throws Exception {
        SeContainerInitializer.newInstance().addPackages(App.class).initialize().select(Bootstrap.class).get().startClient();
    }

}
