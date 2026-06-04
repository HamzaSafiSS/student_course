package com.hamza.studentcourse.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerGroupsConfig {

    @Bean
    public GroupedOpenApi publicApi() {

        return GroupedOpenApi.builder()
                .group("Public APIs")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi protectedApi() {

        return GroupedOpenApi.builder()
                .group("Protected APIs")
                .pathsToMatch("/api/students/**")
                .build();
    }
}