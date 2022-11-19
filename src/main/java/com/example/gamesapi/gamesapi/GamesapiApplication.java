package com.example.gamesapi.gamesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class GamesapiApplication {

	private static final Logger log = LoggerFactory.getLogger(GamesapiApplication.class);
	public static void main(String[] args) {
		log.info("Empiezo la app Gamesapi");
		SpringApplication.run(GamesapiApplication.class, args);
	}

}
