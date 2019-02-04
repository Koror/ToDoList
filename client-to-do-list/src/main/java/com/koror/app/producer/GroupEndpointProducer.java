package com.koror.app.producer;

import com.koror.app.endpoint.GroupEndpoint;
import com.koror.app.endpoint.GroupEndpointService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class GroupEndpointProducer {

    @Produces
    @ApplicationScoped
    public GroupEndpoint getGroupEdnpoint() {
        return new GroupEndpointService().getGroupEndpointPort();
    }

}
