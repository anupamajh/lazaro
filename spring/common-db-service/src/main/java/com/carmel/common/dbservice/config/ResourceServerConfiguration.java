package com.carmel.common.dbservice.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {

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
