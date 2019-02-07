package com.koror.app.config;

import com.koror.app.endpoint.TaskEndpoint;
import com.koror.app.endpoint.TaskEndpointService;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskEndpointProducer {

    @Autowired
    public TaskEndpoint getTaskEdnpoint() {
        return new TaskEndpointService().getTaskEndpointPort();
    }

}
