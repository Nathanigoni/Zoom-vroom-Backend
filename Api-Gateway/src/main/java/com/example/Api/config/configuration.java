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
                                                .uri("https://zoomvroom-user-service.onrender.com"))
                                .route("login", r -> r.path("/User/login/**") // Exclude JWT filter for login if needed
                                                .uri("https://zoomvroom-user-service.onrender.com"))
                                .route("user-update", r -> r.path("/User/update/{id}")
                                                .and().method("PUT")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-user-service.onrender.com"))

                                .route("user-delete", r -> r.path("/User/delete/{id}")
                                                .and().method("DELETE")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-user-service.onrender.com"))

                                .route("ticket-create", r -> r.path("/tickets/create")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-ticket-service.onrender.com"))

                                .route("ticket-book", r -> r.path("/tickets/book/{ticketId}")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-ticket-service.onrender.com"))

                                .route("ticket-cancel", r -> r.path("/tickets/cancel")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-ticket-service.onrender.com"))

                                .route("ticket-get-by-id", r -> r.path("/tickets/{ticketId}")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-ticket-service.onrender.com"))

                                .route("ticket-get-all", r -> r.path("/tickets/all")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-ticket-service.onrender.com"))

                                .route("ticket-by-user", r -> r.path("/tickets/user/{userId}")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-ticket-service.onrender.com"))

                                .route("ticket-save", r -> r.path("/tickets/save")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-ticket-service.onrender.com"))

                                .route("vehicles-add", r -> r.path("/vehicles")
                                                .and().method("POST")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-vehicle-service.onrender.com"))
                                .route("vehicles-get-all", r -> r.path("/vehicles")
                                                .and().method("GET")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroomvehicle-service.onrender.com"))
                                .route("vehicles-by-id", r -> r.path("/vehicles/{vehicleId}")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoom-vroom-vehicle-service.onrender.com"))
                                .route("vehicles-book-seat", r -> r.path("/vehicles/book-seat")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-vehicle-service.onrender.com"))
                                .route("vehicles-available-seats", r -> r.path("/vehicles/{vehicleId}/available-seats")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-vehicle-service.onrender.com"))
                                .route("vehicles-by-type", r -> r.path("/vehicles/type")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-vehicle-service.onrender.com"))
                                .route("vehicles-duration", r -> r.path("/vehicles/{vehicleId}/duration")
                                                .filters(f -> f.filter(jwtfilter.apply(new JWTFilter.Config())))
                                                .uri("https://zoomvroom-vehicle-service.onrender.com"))

                                .build();
        }

}
