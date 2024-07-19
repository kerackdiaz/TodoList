package com.kerackdiaz.Gateway_server.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    public RouteLocator customRouterLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("task-service", r -> r.path("/tasks/**")
                        .filters(f-> f.filter(filter))
                        .uri("lb://task-service"))
                .route("user-service", r -> r.path("/users/**")
                        .filters(f-> f.filter(filter))
                        .uri("lb://user-service"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f-> f.filter(filter))
                        .uri("lb://auth-service"))
                .build();
    }
}
