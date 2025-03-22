package org.example.aadfinal_project.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir:./uploads/images}")
    private String uploadDir;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63342", "http://localhost:8080", "http://127.0.0.1:5500", "*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map URL path "/images/**" to the physical location where images are stored
        Path uploadPath = Paths.get(uploadDir);
        String uploadAbsolutePath = uploadPath.toFile().getAbsolutePath();

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadAbsolutePath + "/");
    }

    private void createDirectoryIfNotExists(String dirPath) {
        try {
            java.io.File directory = new java.io.File(dirPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        } catch (Exception e) {
            // Log the error but don't fail startup
            e.printStackTrace();
        }
    }
}