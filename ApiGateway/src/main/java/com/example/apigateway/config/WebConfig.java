package com.example.apigateway.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autorise toutes les routes
                .allowedOrigins("*") // Permet toutes les origines
                .allowedMethods("*") // Permet toutes les méthodes HTTP (GET, POST, PUT, DELETE, etc.)
                .allowedHeaders("*"); // Autorise tous les headers
    }
}
