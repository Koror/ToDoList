package com.koror.app.config;

import com.koror.app.endpoint.SessionEndpoint;
import com.koror.app.endpoint.SessionEndpointService;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionEndpointProducer {

    @Autowired
    public SessionEndpoint getSessionEdnpoint() {
        return new SessionEndpointService().getSessionEndpointPort();
    }

}
