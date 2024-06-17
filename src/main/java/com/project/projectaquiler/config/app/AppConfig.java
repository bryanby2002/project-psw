package com.project.projectaquiler.config.app;


import javax.sql.DataSource;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Slf4j
public class AppConfig {

  @Value("${database.url}")
  private String urlDB;
  @Value("${database.password}")
  private String passwordDB;

  @Bean
  DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl(urlDB);
    dataSource.setUsername("root");
    dataSource.setPassword(this.passwordDB);
    log.info("Database connection established");
    return dataSource;
  }
}
