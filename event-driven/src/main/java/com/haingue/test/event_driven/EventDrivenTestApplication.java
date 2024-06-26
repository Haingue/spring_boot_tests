package com.haingue.test.event_driven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebFlux
public class EventDrivenTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventDrivenTestApplication.class, args);
	}

}
