package com.dexter.attendanceSystem.config;

import com.dexter.attendanceSystem.audit.AppAuditAware;
import com.dexter.attendanceSystem.exception.StudentException;
import com.dexter.attendanceSystem.repository.UserRepository;
import com.dexter.attendanceSystem.service.UserService;
import com.dexter.attendanceSystem.utils.Errors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@RequiredArgsConstructor

public class AppConfig {

    private  final UserRepository  userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByStudentId(username).orElseThrow( ()-> new StudentException(Errors.USER_DOES_NOT_EXIST));
    }




    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AppAuditAware auditorAware(){
        return new AppAuditAware();
    }







}
