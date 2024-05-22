package com.project.projectaquiler.config.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class AppConfig {

    private Map<String, String> loadEnviromentVariables(){
        Map<String, String> enVars = new HashMap<>();
        enVars.put("DB_HOST", System.getenv("DB_HOST"));
        enVars.put("DB_PORT", System.getenv("DB_PORT"));
        enVars.put("DB_NAME", System.getenv("DB_NAME"));
        enVars.put("DB_USER", System.getenv("DB_USER"));
        enVars.put("DB_PASSWORD", System.getenv("DB_PASSWORD"));
        return enVars;
    }

    @Bean
    DataSource getDataSource() {
        Map<String, String> enVars = loadEnviromentVariables();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://"+enVars.get("DB_HOST")+":"+enVars.get("DB_PORT")+"/"+enVars.get("DB_NAME"));
        dataSource.setUsername(enVars.get("DB_USER"));
        dataSource.setPassword(enVars.get("DB_PASSWORD"));
        log.info("Database connection established");
        return dataSource;
    }
}
