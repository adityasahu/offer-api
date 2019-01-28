package com.aditya.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EntityScan("com.aditya.offers.entity")
@EnableJpaRepositories("com.aditya.offers.repository")
@PropertySource("classpath:db-config.properties")
@EnableScheduling
public class OffersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffersApiApplication.class, args);
	}

}

