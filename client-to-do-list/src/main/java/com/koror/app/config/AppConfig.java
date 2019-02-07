package com.koror.app.config;

import com.koror.app.endpoint.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class AppConfig {

        @Bean
        public GroupEndpoint groupEndpoint() {
            return new GroupEndpointService().getGroupEndpointPort();
        }

        @Bean
        public SessionEndpoint sessionEndpoint() {
            return new SessionEndpointService().getSessionEndpointPort();
        }

        @Bean
        public TaskEndpoint taskEndpoint() {
            return new TaskEndpointService().getTaskEndpointPort();
        }

        @Bean
        public UserEndpoint userEndpoint() {
            return new UserEndpointService().getUserEndpointPort();
        }
    }

