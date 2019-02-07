package com.koror.app.config;

import com.koror.app.endpoint.UserEndpoint;
import com.koror.app.endpoint.UserEndpointService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEndpointProducer {

    @Autowired
    public UserEndpoint getUserEdnpoint() {
        return new UserEndpointService().getUserEndpointPort();
    }

}
