package com.mundackal.UserService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filteringCrieteria(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
//        httpSecurity.authorizeHttpRequests((authorize)->authorize.requestMatchers("/signup*").permitAll());
//        httpSecurity.authorizeHttpRequests((authorize)->authorize.requestMatchers("/login*").permitAll());
        httpSecurity.authorizeHttpRequests((authorize)-> authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
