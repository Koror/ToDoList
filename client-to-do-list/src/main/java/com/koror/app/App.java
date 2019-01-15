package com.koror.app;

import com.koror.app.controller.Bootstrap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
    }
}
