package com.example.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Route {

    @Value("${routes.client-service-url}")
    private String clientServiceUrl;

    @Value("${routes.vehicle-service-url}")
    private String vehicleServiceUrl;

    @Value("${routes.workshop-service-url}")
    private String workshopServiceUrl;

    // Configuration des routes du client
    @Bean
    public RouterFunction<ServerResponse> routerClient() {
        return GatewayRouterFunctions.route("clientRoute")
                .route(RequestPredicates.path("/client/**"), HandlerFunctions.http(clientServiceUrl))
                .build();
    }

    // Configuration des routes du véhicule
    @Bean
    public RouterFunction<ServerResponse> routerVehicle() {
        return GatewayRouterFunctions.route("VehicleRoute")
                .route(RequestPredicates.path("/api/vehicle/**"), HandlerFunctions.http(vehicleServiceUrl))
                .build();
    }

    // Configuration des routes de l'atelier
    @Bean
    public RouterFunction<ServerResponse> routerWorkshop() {
        return GatewayRouterFunctions.route("WorkshopRouter")
                .route(RequestPredicates.path("/workshopPlanning/**"), HandlerFunctions.http(workshopServiceUrl))
                .build();
    }
}
