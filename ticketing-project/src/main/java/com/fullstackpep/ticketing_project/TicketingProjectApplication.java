package com.fullstackpep.ticketing_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//from spring project
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.SpringApplication.run;


@SpringBootApplication
public class TicketingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingProjectApplication.class, args);
	}

}
