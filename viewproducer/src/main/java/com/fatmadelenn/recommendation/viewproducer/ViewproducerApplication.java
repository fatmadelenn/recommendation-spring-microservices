package com.fatmadelenn.recommendation.viewproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ViewproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewproducerApplication.class, args);
	}

}
