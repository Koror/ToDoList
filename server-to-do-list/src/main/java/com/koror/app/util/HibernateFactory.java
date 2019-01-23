package com.koror.app.util;

import com.koror.app.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class HibernateFactory {

    public static SessionFactory sessionFactory;

    public static void buildFactory() {
        final Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, AppConfig.JDBC_DRIVER);
        settings.put(Environment.URL, AppConfig.URL);
        settings.put(Environment.USER, AppConfig.USER);
        settings.put(Environment.PASS, AppConfig.PASSWORD);
        settings.put(Environment.DIALECT, AppConfig.HIBERNATE_DIALECT);
        settings.put(Environment.HBM2DDL_AUTO, AppConfig.HBM2DDL_AUTO);
        settings.put(Environment.SHOW_SQL, AppConfig.HIBERNATE_SHOW_SQL);

        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        sources.addAnnotatedClass(Group.class);
        sources.addAnnotatedClass(AssigneeTask.class);
        sources.addAnnotatedClass(AssigneeGroup.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

}
