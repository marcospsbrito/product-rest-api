package com.avenuecode.recruitment;

/**
 * Created by marcos on 2/5/18.
 */

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class AppConfig extends ResourceConfig {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    public AppConfig() {
        register(ProductEndpoint.class);
    }
}
