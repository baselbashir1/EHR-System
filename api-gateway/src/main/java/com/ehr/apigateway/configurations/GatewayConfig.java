package com.ehr.apigateway.configurations;

import com.ehr.apigateway.jwt.JwtAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public GatewayConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user/**")
                        .filters(f -> f.filter(jwtAuthFilter))
                        .uri("http://localhost:8081"))
                .route("user-service", r -> r.path("/auth/**")
                        .uri("http://localhost:8081"))
                .route("clinic-service", r -> r.path("/clinic/**")
                        .uri("http://localhost:8083"))
                .route("appointment-service", r -> r.path("/appointment/**")
                        .uri("http://localhost:8084"))
                .build();
    }

}