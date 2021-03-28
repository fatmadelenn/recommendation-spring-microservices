package com.fatmadelenn.recommendation.streamreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamreaderApplication.class, args);
	}

}
