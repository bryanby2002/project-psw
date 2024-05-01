package com.project.projectaquiler;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class ProjectAquilerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAquilerApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("256KB")); // Establece el tamaño máximo del archivo
        factory.setMaxRequestSize(DataSize.parse("512KB")); // Establece el tamaño máximo de la solicitud
        return factory.createMultipartConfig();
    }

}
