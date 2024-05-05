package com.board.FeatureHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 요청에 대해 CORS 적용
                .allowedOrigins("http://localhost:3000", "http://localhost:80")
                .allowedMethods("GET", "POST", "PUT", "PATCH") // 모든 HTTP 메소드 허용
                .allowedHeaders("*"); // 모든 Header 허용
    }
}