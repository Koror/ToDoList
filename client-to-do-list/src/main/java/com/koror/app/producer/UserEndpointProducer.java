package com.koror.app.producer;

import com.koror.app.endpoint.UserEndpoint;
import com.koror.app.endpoint.UserEndpointService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class UserEndpointProducer {

    @Produces
    @ApplicationScoped
    public UserEndpoint getUserEdnpoint() {
        return new UserEndpointService().getUserEndpointPort();
    }

}
