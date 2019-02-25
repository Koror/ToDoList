package com.koror.app.config;

import com.sun.faces.config.FacesInitializer;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer extends FacesInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(ApplicationConfig.class);
        sc.addListener(new ContextLoaderListener(root));

        ServletRegistration.Dynamic dispatcher
                = sc.addServlet("dispatcher", new CXFServlet());
        dispatcher.addMapping("/soap/*");
        dispatcher.setLoadOnStartup(1);
    }
}
