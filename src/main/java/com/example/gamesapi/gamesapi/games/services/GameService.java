package com.example.gamesapi.gamesapi.games.services;

import com.example.gamesapi.gamesapi.games.model.Game;

import com.example.gamesapi.gamesapi.games.repository.GameRepository;
import com.example.gamesapi.gamesapi.utils.JsonConverterService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("gameServices")
public class GameService {

    @Value("${api.name}")
    String apiName;

    @Value("${api.key}")
    String apiKey;

    /*API URLs*/
    private static final String ALLGAMES ="games";

    private RestTemplate restTemplate;
    private JsonConverterService jsonConverter;
    private GameRepository gameRepository;

    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    @Autowired
    public GameService(RestTemplate restTemplate, JsonConverterService jsonConverter, GameRepository gameRepository) {
        this.restTemplate = restTemplate;
        this.jsonConverter = jsonConverter;
        this.gameRepository = gameRepository;
    }

    public void saveListOfGames() {
        String url = apiName + ALLGAMES + apiKey;
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), String.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
            String jsonString =  response.getBody().toString();
            JSONObject json = jsonConverter.getJSONObjectForJSONString(jsonString);

            try{
                JSONArray array = json.getJSONArray("results");
                List<Game> games = new ArrayList<>();
                for (int i = 0 ; i< array.length();i++){
                    Game game = new Game();
                    int id = array.getJSONObject(i).getInt("id");
                    String name = array.getJSONObject(i).getString("name");
                    String rating = array.getJSONObject(i).getString("rating");
                    String image = array.getJSONObject(i).getString("background_image");
                    String released = array.getJSONObject(i).getString("released");
                    game.setId(id);
                    game.setName(name);
                    game.setRating(rating);
                    game.setBackGroundImage(image);
                    game.setReleased(released);
                    games.add(game);
                    gameRepository.save(game);
                }
            }catch (Exception e){
                log.error("Error: {}", e.getMessage());
            }

        }
    }

    public List<Game> getListOfGames(){
        Optional<List<Game>> gamesOpt = gameRepository.findAllGamesOrderById();
        if (!gamesOpt.isPresent()){
            return Collections.emptyList();
        }
        return gamesOpt.get();
    }

    public Game findById(int id){
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (!gameOpt.isPresent()){
            return null;
        }
        return gameOpt.get();
    }
}
