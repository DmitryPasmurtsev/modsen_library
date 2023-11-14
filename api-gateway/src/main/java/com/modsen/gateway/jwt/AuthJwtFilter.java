package com.modsen.gateway.jwt;

import com.modsen.gateway.configs.RouterValidator;
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
public class AuthJwtFilter implements GatewayFilter {

    private static final int JWT_START_INDEX_IN_TOKEN = 7;
    private static final String TOKEN_PREFIX = "Bearer ";

    private final RouterValidator validator;
    private final JwtService jwtService;

    public AuthJwtFilter(RouterValidator validator, JwtService jwtService){
       this.validator = validator;
       this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (validator.isSecured.test(request)) {
            if (authMissing(request)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            if (token != null && token.startsWith(TOKEN_PREFIX)) {
                token = token.substring(JWT_START_INDEX_IN_TOKEN);
            }
            if (!jwtService.validateToken(token)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            if (jwtService.isExpired(token)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
