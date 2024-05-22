package com.project.projectaquiler.config.security;

import com.project.projectaquiler.services.auth.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests(httpRequest -> {
                    // endpoinst publicos
                    // vehiculos
                    httpRequest.requestMatchers(HttpMethod.GET, "/vehicle/list").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET, "vehicle/search/{palabra}").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET, "vehicle/filter").permitAll();
                    httpRequest.requestMatchers(HttpMethod.POST, "/user/create").permitAll();

                    //endpoinst configurados
                    // vehiculos
                    httpRequest.requestMatchers(HttpMethod.POST, "/vehicle/create").hasRole("ADMIN");
                    httpRequest.requestMatchers(HttpMethod.PATCH, "vehicle/update").hasRole("ADMIN");

                    // user
                    httpRequest.requestMatchers(HttpMethod.POST, "/user/list").hasRole("ADMIN");
                    httpRequest.requestMatchers(HttpMethod.PATCH, "/user/bookings")
                            .hasAnyRole("USER", "ADMIN");

                    // booking
                    httpRequest.requestMatchers(HttpMethod.POST, "/booking/create");

                    // endpoints no configuradas
                    httpRequest.anyRequest().denyAll();
                })
                .build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider provider(UserDetailsImpl userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
