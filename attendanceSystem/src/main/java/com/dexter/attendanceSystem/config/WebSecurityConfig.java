package com.dexter.attendanceSystem.config;

import com.dexter.attendanceSystem.config.jwtConfig.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    private static  final String [] WHITE_LIST_URLS = {

            "/api/sign-up",
            "api/login"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(withDefaults())
                .csrf(csrf -> csrf.disable() )
                .authorizeRequests(
                        authorize ->{
                            try {
                                authorize
                                        .requestMatchers(WHITE_LIST_URLS).permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                        .authenticationProvider(authenticationProvider)
                                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                            } catch (Exception e) {
                                throw new RuntimeException(e);

                            }

                        }
                );
        return httpSecurity.build();
    }
}
