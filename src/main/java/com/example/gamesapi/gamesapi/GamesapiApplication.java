package com.example.gamesapi.gamesapi;

import com.example.gamesapi.gamesapi.developers.services.DevelopersService;
import com.example.gamesapi.gamesapi.games.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class GamesapiApplication {

	private static final Logger log = LoggerFactory.getLogger(GamesapiApplication.class);
	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(GamesapiApplication.class, args);
		GameService service = run.getBean(GameService.class);
		service.saveListOfGames();

		DevelopersService developersService = run.getBean(DevelopersService.class);
		developersService.saveDevelopers();
	}

}
