package com.ironhack.student_catalog_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    /**
     * Creates a load-balanced {@link RestTemplate} bean for making HTTP requests.
     *
     * @return a {@code RestTemplate} instance with client-side load balancing enabled
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
}
