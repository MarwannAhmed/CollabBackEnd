package com.cmps211.collab.editor.Configuration;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins("https://collaborativeeditor.vercel.app") // Replace with your client's URL
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Add allowed methods as required
                .allowedHeaders("*"); // Allow all headers

        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins("https://collaborativeeditor.vercel.app/*") // Allow requests from the specified origin and its subpaths
                .allowedMethods("*") // Allow all HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }
}
