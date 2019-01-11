package com.koror.app;

import com.koror.app.controller.Bootstrap;
import com.koror.app.error.WrongInputException;

public class App {

    public static void main(String[] args) throws ReflectiveOperationException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
    }

}
