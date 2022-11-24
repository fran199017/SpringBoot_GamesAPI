package com.example.gamesapi.gamesapi.games.controller;

import com.example.gamesapi.gamesapi.games.model.Game;
import com.example.gamesapi.gamesapi.games.model.GameSpecification;
import com.example.gamesapi.gamesapi.games.model.GamesCriteria;
import com.example.gamesapi.gamesapi.games.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/public")
public class PublicController {

    GameService gameService;

    @Autowired
    public PublicController(GameService gameService) {
        this.gameService = gameService;
    }



    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @GetMapping(value = "/index")
    public ModelAndView index(){
        try{

            ModelAndView model = new ModelAndView();
            model.setViewName("index");
            return model;
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }

    @GetMapping(value = "/games")
    public ModelAndView games(int page, String key, String value ){
        try{
            GamesCriteria gamesCriteria = null;

            if (key!= null && value != null){
                gamesCriteria = new GamesCriteria();
                gamesCriteria.setKey(key);
                gamesCriteria.setValue(value);
            }


            ModelAndView model = new ModelAndView();
            model.setViewName("games");
            Page<Game> listOfGames;

            if (gamesCriteria != null){
                GameSpecification gameSpecification = new GameSpecification(gamesCriteria);
                List<Game> games = gameService.getListOfGamesWithCriteria(gameSpecification);
                Pageable pageable = PageRequest.of(0, games.size());
                listOfGames = new PageImpl<>(games,pageable, games.size());
                model.addObject("listOfGames", listOfGames);
            }else{
                Pageable pageable = PageRequest.of(page -1,30);
                listOfGames = gameService.getListOfGames(pageable);
                model.addObject("listOfGames", listOfGames);
            }

            int totalPages = listOfGames.getTotalPages();
            if (totalPages > 0){
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addObject("pageNumbers", pageNumbers);
            }
            model.addObject("page", page);
            return model;
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
