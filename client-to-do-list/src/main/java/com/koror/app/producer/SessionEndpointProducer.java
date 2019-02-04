package com.koror.app.producer;

import com.koror.app.endpoint.SessionEndpoint;
import com.koror.app.endpoint.SessionEndpointService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class SessionEndpointProducer {

    @Produces
    @ApplicationScoped
    public SessionEndpoint getSessionEdnpoint() {
        return new SessionEndpointService().getSessionEndpointPort();
    }

}
