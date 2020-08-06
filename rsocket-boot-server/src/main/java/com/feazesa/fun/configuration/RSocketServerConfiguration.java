package com.feazesa.fun.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RSocketServerConfiguration {

    @Value("${spring.rsocket.server.address}")
    private String address;
    @Value("${spring.rsocket.server.port}")
    private int port;
}
