package com.example.gamesapi.gamesapi.developers.services;

import com.example.gamesapi.gamesapi.developers.model.Developers;
import com.example.gamesapi.gamesapi.developers.repository.DevelopersRepository;
import com.example.gamesapi.gamesapi.games.model.Game;
import com.example.gamesapi.gamesapi.games.services.GameService;
import com.example.gamesapi.gamesapi.utils.JsonConverterService;
import org.json.JSONArray;
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

import java.util.ArrayList;
import java.util.List;

@Service("developersService")
public class DevelopersService {

    @Value("${api.name}")
    String apiName;

    @Value("${api.key}")
    String apiKey;

    private static final String DEVELOPERS ="developers";
    private static final int PAGE_SIZE = 40;


    private static final Logger log = LoggerFactory.getLogger(DevelopersService.class);

    private DevelopersRepository developersRepository;
    private RestTemplate restTemplate;
    private JsonConverterService jsonConverter;

    @Autowired
    public DevelopersService(DevelopersRepository developersRepository, RestTemplate restTemplate, JsonConverterService jsonConverter) {
        this.developersRepository = developersRepository;
        this.restTemplate = restTemplate;
        this.jsonConverter = jsonConverter;
    }

    public Page<Developers> getListOfDevelopers(Pageable pageable) {
        return developersRepository.findAll(pageable);
    }

    public void saveDevelopers() {
        for(int page = 1; page < 5; page++ ){
            String url = apiName + DEVELOPERS + apiKey + "&page_size="+ PAGE_SIZE + "&page=" + page;
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), String.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
                String jsonString =  response.getBody().toString();
                JSONObject json = jsonConverter.getJSONObjectForJSONString(jsonString);
                try{
                    JSONArray array = json.getJSONArray("results");
                    List<Developers> developers = new ArrayList<>();

                    for (int i = 0 ; i< array.length();i++){
                        Developers developer = new Developers();

                        int id = array.getJSONObject(i).getInt("id");
                        int gamesCount = array.getJSONObject(i).getInt("games_count");
                        String name = array.getJSONObject(i).getString("name");
                        String image = array.getJSONObject(i).getString("image_background");

                        developer.setId(id);
                        developer.setBackGroundImage(image);
                        developer.setGamesCount(gamesCount);
                        developer.setName(name);
                        developers.add(developer);
                        developersRepository.save(developer);
                    }
                }catch (Exception e){
                    log.error("Error: {}", e.getMessage());
                }

            }
        }
    }
}
