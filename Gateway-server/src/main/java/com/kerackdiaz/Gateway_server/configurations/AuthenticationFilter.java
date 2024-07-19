package com.kerackdiaz.Gateway_server.configurations;

import com.kerackdiaz.Gateway_server.services.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private RouterValidator validator;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (validator.isSecured.test(request)) {
           if(autMissing(request)){
               return onError(exchange, HttpStatus.UNAUTHORIZED);
           }
           final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

           if (!jwtUtils.isTokenExpired(token)) {
               return onError(exchange, HttpStatus.UNAUTHORIZED);
           }
        }
        return null;
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        return response.setComplete();
    }

    private boolean autMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
