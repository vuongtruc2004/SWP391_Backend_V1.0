package com.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Value("${assets.uri}")
    private String assetURI;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // check: 1/2/2025 5h chiều
        registry.addResourceHandler("/storage/**")
                .addResourceLocations(assetURI);
    }
}
