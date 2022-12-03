package com.example.gamesapi.gamesapi.games.services;

import com.example.gamesapi.gamesapi.games.model.Game;

import com.example.gamesapi.gamesapi.games.model.GameSpecification;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private static final int PAGE_SIZE = 40;

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
        for(int page = 1; page < 5; page++ ){
            String url = apiName + ALLGAMES + apiKey + "&page_size="+ PAGE_SIZE + "&page=" + page;
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

                        JSONObject gameJSON = array.getJSONObject(i);
                        JSONArray generes = gameJSON.getJSONArray("genres");
                        List<String> generesStr = new ArrayList<>();

                        for (int j = 0; j< generes.length();j++){
                            generesStr.add(generes.getJSONObject(j).getString("name"));
                        }

                        game.setGeneres(String.join(",", generesStr));

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
    }


    public Game findByPlatform(int id) {
        for(int page = 1; page < 5; page++ ){
            String url = apiName + ALLGAMES + apiKey + "&page_size="+ PAGE_SIZE + "&page=" + page + "&platforms=" + id;
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), String.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
                log.info("Response {}" , response.getBody());
            }
        }
        return  null;
    }

    public Page<Game> getListOfGames(Pageable pageable){
        Page<Game> page = gameRepository.findAll(pageable);
        if (page.isEmpty()){
            return Page.empty();
        }
        return page;
    }

    public List<Game> getListOfGamesWithCriteria(GameSpecification spec) {
        List<Game> games = gameRepository.findAll(spec);
        if (games.isEmpty()){
            return Collections.emptyList();
        }
        return games;
    }

    public Game findById(int id){
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (!gameOpt.isPresent()){
            return null;
        }
        return gameOpt.get();
    }


    public void deleteGame(int id) {
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (gameOpt.isPresent()){
            Game game = gameOpt.get();
            gameRepository.delete(game);
        }
    }

}
