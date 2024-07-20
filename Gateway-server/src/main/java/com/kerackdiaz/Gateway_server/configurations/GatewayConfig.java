package com.kerackdiaz.Gateway_server.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator customRouterLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("task-service", r -> r.path("/tasks/**")
                        .filters(f-> f.filter(filter))
                        .uri("lb://task-service"))
                .route("User-Service", r -> r.path("/users/**")
                        .filters(f-> f.filter(filter))
                        .uri("lb://User-Service"))
                .route("AuthServer", r -> r.path("/auth/**")
                        .filters(f-> f.filter(filter))
                        .uri("lb://AuthServer"))
                .route("Gateway-server", r -> r.path("/swagger-ui/**")
                        .filters(f-> f.filter(filter))
                        .uri("lb://Gateway-server"))
                .build();
    }
}
