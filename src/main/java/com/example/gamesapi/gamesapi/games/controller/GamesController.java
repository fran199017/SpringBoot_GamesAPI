package com.example.gamesapi.gamesapi.games.controller;

import com.example.gamesapi.gamesapi.GamesapiApplication;
import com.example.gamesapi.gamesapi.games.model.Game;
import com.example.gamesapi.gamesapi.games.services.GameService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class GamesController {

    /*HTTP STATUS*/
    private static final String COMMAND_EXECUTE_SUCCESFULLY = "Command executed succesfully";
    private static final String BAD_REQUEST = "Bad Request";
    private static final String SERVER_EXCEPTION = "Server Exception";
    private static final String UNAUTHORIZED = "Unauthorized";

    private static final Logger log = LoggerFactory.getLogger(GamesController.class);
    private static final String NO_GAMES = "Remember first add games of API";

    GameService gameService;

    @Autowired
    public GamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "games/save")
    @ApiOperation(value = "Save list of games of rawg.io API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = SERVER_EXCEPTION),
            @ApiResponse(code = 200, message = COMMAND_EXECUTE_SUCCESFULLY, response = List.class),
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 401, message = UNAUTHORIZED)
    })
    public ResponseEntity<Object> saveListOfGames() {
        try{
            gameService.saveListOfGames();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "games")
    @ApiOperation(value = "Get all games from h2 database")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = SERVER_EXCEPTION),
            @ApiResponse(code = 200, message = COMMAND_EXECUTE_SUCCESFULLY, response = List.class),
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 401, message = UNAUTHORIZED)
    })
    public ResponseEntity<Object> getListOfGames(int page) {
        try{
            Pageable pageable = PageRequest.of(page -1,14);
            Page<Game> listOfGames = gameService.getListOfGames(pageable);
            if (!listOfGames.isEmpty()){
                return new ResponseEntity<>(listOfGames,HttpStatus.OK);
            }
            return new ResponseEntity<>(NO_GAMES,HttpStatus.NOT_FOUND);

        }catch (Exception e) {
            log.error(e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "games/{id}")
    @ApiOperation(value = "Get game by ID from h2 database")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = SERVER_EXCEPTION),
            @ApiResponse(code = 200, message = COMMAND_EXECUTE_SUCCESFULLY, response = List.class),
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 401, message = UNAUTHORIZED)
    })
    public ResponseEntity<Object> getGameById(@PathVariable @ApiParam(value = "Game's id", required = true) int id) {
        try{
            Game game = gameService.findById(id);
            if (game != null){
                return new ResponseEntity<>(game,HttpStatus.OK);
            }
            return new ResponseEntity<>(NO_GAMES,HttpStatus.NOT_FOUND);

        }catch (Exception e) {
            log.error(e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
