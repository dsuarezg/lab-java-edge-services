package org.ironhack.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("student",r->r.path("/api/student/**").uri("lb://student-info-service"))
                .route("course",r->r.path("/api/course/**").uri("lb://grade-data-service"))
                .route("grade",r->r.path("/api/grade/**").uri("lb://grade-data-service"))
                .route("catalog",r->r.path("/api/catalog/**").uri("lb://student-catalog-service"))
                .build();
    }
}
