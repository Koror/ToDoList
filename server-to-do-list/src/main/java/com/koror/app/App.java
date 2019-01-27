package com.koror.app;

import com.koror.app.controller.Bootstrap;
import com.koror.app.error.WrongInputException;
import com.koror.app.util.AppConfig;
import com.koror.app.util.DatabaseConnection;
import com.koror.app.util.HibernateFactory;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws ReflectiveOperationException, IOException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.startServer();
    }

}
