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
                                .route("register", r -> r.path("/User/register/**") // No filter for registration
                                                .uri("https://zoom-vroom-user-service.onrender.com"))
                                .route("login", r -> r.path("/User/login/**") // Exclude JWT filter for login if needed
                                                .uri("https://zoom-vroom-user-service.onrender.com"))

                                .route("Ticket-book", r -> r.path("/ticket/book**")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-ticket-service.onrender.com"))
                                .route("Ticket-cancel", r -> r.path("/ticket/cancel/{ticketId}**")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-ticket-service.onrender.com"))
                                .route("Ticket-get", r -> r.path("/ticket/{ticketId}**")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-ticket-service.onrender.com"))
                                .route("Ticket-create", r -> r.path("/ticket/create**")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-ticket-service.onrender.com"))
                                .route("Ticket-all", r -> r.path("/ticket/all**")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-ticket-service.onrender.com"))

                                .route("vehicles-add", r -> r.path("/vehicles")
                                                .and().method("POST")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-vehicle-service.onrender.com"))
                                .route("vehicles-get-all", r -> r.path("/vehicles")
                                                .and().method("GET")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-vehicle-service.onrender.com"))
                                .route("vehicles-by-id", r -> r.path("/vehicles/{vehicleId}")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-vehicle-service.onrender.com"))
                                .route("vehicles-book-seat", r -> r.path("/vehicles/book-seat")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-vehicle-service.onrender.com"))
                                .route("vehicles-available-seats", r -> r.path("/vehicles/{vehicleId}/available-seats")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-vehicle-service.onrender.com"))
                                .route("vehicles-by-type", r -> r.path("/vehicles/type")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-vehicle-service.onrender.com"))
                                .route("vehicles-duration", r -> r.path("/vehicles/{vehicleId}/duration")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-backend-vehicle-service.onrender.com"))

                                .build();
        }

}
