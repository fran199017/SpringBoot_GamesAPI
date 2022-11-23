package com.example.gamesapi.gamesapi;

import com.example.gamesapi.gamesapi.games.controller.GamesController;
import com.example.gamesapi.gamesapi.games.model.Game;
import com.example.gamesapi.gamesapi.games.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class GamesapiApplication {

	static GameService gameService;

	@Autowired
	public GamesapiApplication(GameService gameService) {
		this.gameService = gameService;
	}



	private static final Logger log = LoggerFactory.getLogger(GamesapiApplication.class);
	public static void main(String[] args) {
		log.info("Empiezo la app Gamesapi");
		ConfigurableApplicationContext run = SpringApplication.run(GamesapiApplication.class, args);
		GameService service = run.getBean(GameService.class);
		service.saveListOfGames();
	}

}
