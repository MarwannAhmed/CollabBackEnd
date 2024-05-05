package com.cmps211.collab.editor.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins("https://collaborativeeditor.vercel.app") // Allow requests from the specified origin
                .allowedMethods("*") // Allow all HTTP methods
                .allowedHeaders("*"); // Allow all headers

        // If you also want to allow requests from any subpath of the specified origin:
        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins("https://collaborativeeditor.vercel.app/*") // Allow requests from the specified origin and its subpaths
                .allowedMethods("*") // Allow all HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }
}
