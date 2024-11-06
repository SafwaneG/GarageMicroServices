package com.example.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Route {

    @Bean
    public RouterFunction<ServerResponse> routerClient() {
        return GatewayRouterFunctions.route("clientRoute")
                .route(RequestPredicates.path("/client"), HandlerFunctions.http("http://localhost:8081"))
                .route(RequestPredicates.path("/client/{id}"), request ->
                        HandlerFunctions.http("http://localhost:8081/client/" + request.pathVariable("id")).handle(request))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> routerClientSwaggerRoute() {
        return GatewayRouterFunctions.route("client_service_swagger")
                .route(RequestPredicates.path("/aggregate/client-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(setPath("/v3/api-docs"))
               .build();
    }
    @Bean
    public RouterFunction<ServerResponse> routerVehicleSwaggerRoute() {
        return GatewayRouterFunctions.route("vehicle_service_swagger")
                .route(RequestPredicates.path("/aggregate/vehicle-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/v3/api-docs"))
                .build();
    }
      @Bean
    public RouterFunction<ServerResponse> routerWorkshopSwaggerRoute() {
        return GatewayRouterFunctions.route("workshop_service_swagger")
                .route(RequestPredicates.path("/aggregate/workshop-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(setPath("/v3/api-docs"))
               .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerVehicle() {
        return GatewayRouterFunctions.route("VehicleRoute")
                .route(RequestPredicates.path("/api/vehicle"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerWorkshop() {
        return GatewayRouterFunctions.route("WorkshopRouter")
                .route(RequestPredicates.path("/workshopPlanning"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }
}
