package com.example.Api.config;

import com.example.Api.security.JWTFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configuration {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, JWTFilter jwtfilter) {
        return builder.routes()
                .route("register", r -> r.path("/User/register/**")  // No filter for registration
                        .uri("http://localhost:8094"))
                .route("login", r -> r.path("/User/login/**")  // Exclude JWT filter for login if needed
                        .uri("http://localhost:8094"))
                .route("Ticket-book", r -> r.path("/ticket/book**")
                      .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                      .uri("http://localhost:8080"))
                .route("Ticket-cancel", r -> r.path("/ticket/cancel/{ticketId}**")
                        .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                        .uri("http://localhost:8080"))
                .route("Ticket-get", r -> r.path("/ticket/{ticketId}**")
                        .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                        .uri("http://localhost:8080"))
                .route("vehicles", r -> r.path("/vehicles/book-seat**")
                        .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                        .uri("http://localhost:8090"))
                .route("vehicles", r -> r.path("/vehicles/{vehicleId}/available-seats**")
                        .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                        .uri("http://localhost:8090"))
                .route("vehicles", r -> r.path("/{vehicleId}**")
                        .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                        .uri("http://localhost:8090"))
                .route("vehicles-all", r -> r.path("/vehicles**")
                        .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                        .uri("http://localhost:8090"))
                .route("vehicles-by-id", r -> r.path("/vehicles**")
                        .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                        .uri("http://localhost:8090"))
                .build();
    }

}
