package com.nononsensecode.secure.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/api/v1.0/greetings/user/{userId:\\d+}")
                        .access("@userValidator.validate(#userId)")
                    .antMatchers("/api/v1.0/hello")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                .and()
                .formLogin();

    }
}
