package com.carmel.common.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class CommonDbServiceApplication {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DataSource dataSource;

    @Bean
    TokenStore jdbcTokenStore(){
        return  new JdbcTokenStore(dataSource);
    }


    public static void main(String[] args) {
        SpringApplication.run(CommonDbServiceApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
