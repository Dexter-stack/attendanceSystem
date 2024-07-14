package com.dexter.attendanceSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@RequiredArgsConstructor

public class AppConfig {



    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(11);
    }


}
