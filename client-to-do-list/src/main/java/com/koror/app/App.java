package com.koror.app;

import com.koror.app.controller.Bootstrap;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
        annotationContext.scan("com.koror.app");
        annotationContext.refresh();
        Bootstrap bootstrap = annotationContext.getBean("bootstrap", Bootstrap.class);
        bootstrap.startClient();
    }

}
