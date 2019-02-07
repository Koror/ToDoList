package com.koror.app.config;

import com.koror.app.endpoint.GroupEndpoint;
import com.koror.app.endpoint.GroupEndpointService;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupEndpointProducer {

    @Autowired
    public GroupEndpoint getGroupEdnpoint() {
        return new GroupEndpointService().getGroupEndpointPort();
    }

}
