package com.chatbot.Sahayakam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/ChatBotAPI/**")
                .allowedOrigins("http://localhost:4200  ") // Specify the allowed origin(s)
                .allowedMethods("*") // Specify the allowed HTTP methods
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(-1); // Specify the allowed headers
    }


}
