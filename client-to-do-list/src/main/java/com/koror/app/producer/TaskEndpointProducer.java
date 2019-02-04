package com.koror.app.producer;

import com.koror.app.endpoint.TaskEndpoint;
import com.koror.app.endpoint.TaskEndpointService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class TaskEndpointProducer {

    @Produces
    @ApplicationScoped
    public TaskEndpoint getTaskEdnpoint() {
        return new TaskEndpointService().getTaskEndpointPort();
    }

}
