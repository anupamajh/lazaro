package com.carmel.guestjini.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class GuestjiniServiceApplication {


	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@ConfigurationProperties("carmel.oauth2.client")
	protected ClientCredentialsResourceDetails carmelOAuthDetails() {
		return new ClientCredentialsResourceDetails();
	}

	@LoadBalanced
	@Bean
	RestTemplate clientAuthenticated() {
		return new OAuth2RestTemplate(carmelOAuthDetails());
	}



	@PostConstruct
	public void setupServices(){

	}

	public static void main(String[] args) {
		SpringApplication.run(GuestjiniServiceApplication.class, args);
	}

}
