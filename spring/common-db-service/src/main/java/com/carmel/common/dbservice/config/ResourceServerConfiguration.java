package com.carmel.common.dbservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

public class ResourceServerConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/user/reset-password").permitAll()
                .anyRequest().authenticated();
   /* http.authorizeRequests()
                .antMatchers("/user/reset-password**")
                .permitAll()
                .anyRequest().authenticated();*/
    }
}
