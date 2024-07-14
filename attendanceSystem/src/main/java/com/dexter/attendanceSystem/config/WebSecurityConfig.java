package com.dexter.attendanceSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static  final String [] WHITE_LIST_URLS = {

            "/api/sign-up"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(withDefaults())
                .csrf(csrf -> csrf.disable() )
                .authorizeRequests(
                        authorize ->{
                            authorize
                                    .requestMatchers(WHITE_LIST_URLS).permitAll()
                                    .anyRequest().authenticated();

                        }
                );
        return httpSecurity.build();
    }
}
