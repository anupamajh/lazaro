package com.carmel.common.datamigrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class DataMigratorApplication {
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


	public static void main(String[] args) {
		SpringApplication.run(DataMigratorApplication.class, args);
	}

}
