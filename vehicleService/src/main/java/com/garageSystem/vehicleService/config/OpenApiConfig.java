package com.garageSystem.vehicleService.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI swaggerConfig() {
        return new OpenAPI().info(
                new Info().title("Vehicle service API")
                        .description("This is the Rest api for Vehicle service")
                        .version("1.0")
                        .license(new License().name("Apache 2.0"))
        );
    }
}