package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable if using API + JWT
                .authorizeHttpRequests(auth -> auth
                        // 👇 Public endpoints
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/review/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/images/**").permitAll()


                        // 👇 Everything else requires authentication
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
