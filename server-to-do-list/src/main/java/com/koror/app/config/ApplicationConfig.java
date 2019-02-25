package com.koror.app.config;

import com.koror.app.endpoint.GroupEndpoint;
import com.koror.app.util.PropertyConfig;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.util.Properties;

@ImportResource("classpath:*applicationContext.xml")
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories("com.koror.app.api.repository")
@ComponentScan("com.koror.app")
public class ApplicationConfig implements WebMvcConfigurer {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.koror.app.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", PropertyConfig.HBM2DDL_AUTO);
        properties.setProperty(
                "hibernate.dialect", PropertyConfig.HIBERNATE_DIALECT);
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject() );
        return transactionManager;
    }

    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PropertyConfig.JDBC_DRIVER);
        dataSource.setUrl(PropertyConfig.URL);
        dataSource.setUsername(PropertyConfig.USER);
        dataSource.setPassword(PropertyConfig.PASSWORD);
        return dataSource;
    }

}
