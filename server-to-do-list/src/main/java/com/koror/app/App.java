package com.koror.app;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.controller.Bootstrap;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) throws ReflectiveOperationException {
//        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
//        annotationContext.scan("com.koror.app");
//        annotationContext.refresh();
//        Bootstrap bootstrap = annotationContext.getBean("bootstrap", Bootstrap.class);
//        bootstrap.startServer();

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-persistence.xml");
        ctx.getBean("bootstrap", Bootstrap.class).startServer();
    }

}
