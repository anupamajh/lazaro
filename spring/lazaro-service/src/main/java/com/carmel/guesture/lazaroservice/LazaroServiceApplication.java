package com.carmel.guesture.lazaroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LazaroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazaroServiceApplication.class, args);
	}

}
