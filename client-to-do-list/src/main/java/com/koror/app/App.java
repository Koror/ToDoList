package com.koror.app;

import com.koror.app.controller.Bootstrap;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.clientStart();
    }
}
