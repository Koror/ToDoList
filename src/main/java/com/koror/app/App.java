package com.koror.app;

import com.koror.app.controller.Bootstrap;

import java.util.HashSet;
import java.util.Set;

public class App {

    public static void main(String[] args) throws ReflectiveOperationException{
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
        Set<Object> set = new HashSet<>();
    }

}
