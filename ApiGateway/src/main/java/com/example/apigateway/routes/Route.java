package com.example.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Route {
    @Bean
    public RouterFunction<ServerResponse> routerClient(){
        return GatewayRouterFunctions.route("clientRoute")
                .route(RequestPredicates.path("/client"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> routerVehicle(){
        return GatewayRouterFunctions.route("VehicleRoute")
                .route(RequestPredicates.path("/api/vehicle"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }
}
