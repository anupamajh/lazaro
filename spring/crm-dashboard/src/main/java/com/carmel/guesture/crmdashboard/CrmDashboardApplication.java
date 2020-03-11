package com.carmel.guesture.crmdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CrmDashboardApplication {

	@Bean
	@ConfigurationProperties("olive-crm.oauth2.client")
	protected ClientCredentialsResourceDetails oAuthDetails() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	protected RestTemplate restTemplate() {
		return new OAuth2RestTemplate(oAuthDetails());
	}

	public static void main(String[] args) {
		SpringApplication.run(CrmDashboardApplication.class, args);
	}

}
