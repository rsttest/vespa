// Copyright Yahoo. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.hosted.node.admin.configserver.state;

import com.yahoo.vespa.hosted.node.admin.configserver.ConfigServerApi;
import com.yahoo.vespa.hosted.node.admin.configserver.ConnectionException;
import com.yahoo.vespa.hosted.node.admin.configserver.HttpException;
import com.yahoo.vespa.hosted.node.admin.configserver.state.bindings.HealthResponse;

/**
 * @author hakon
 */
public class StateImpl implements State {
    private final ConfigServerApi configServerApi;

    public StateImpl(ConfigServerApi configServerApi) {
        this.configServerApi = configServerApi;
    }

    @Override
    public HealthCode getHealth() {
        try {
            HealthResponse response = configServerApi.get("/state/v1/health", HealthResponse.class);
            return HealthCode.fromString(response.status().code());
        } catch (ConnectionException | HttpException e) {
            return HealthCode.DOWN;
        }
    }

}
