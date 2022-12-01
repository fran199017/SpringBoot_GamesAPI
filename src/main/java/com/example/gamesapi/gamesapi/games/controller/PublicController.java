package com.example.gamesapi.gamesapi.games.controller;

import com.example.gamesapi.gamesapi.games.model.Game;
import com.example.gamesapi.gamesapi.games.model.GameSpecification;
import com.example.gamesapi.gamesapi.games.model.GamesCriteria;
import com.example.gamesapi.gamesapi.games.services.GameService;
import io.swagger.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*@Controller*/
@RequestMapping("/public")
@RestController
public class PublicController {

    GameService gameService;

    @Autowired
    public PublicController(GameService gameService) {
        this.gameService = gameService;
    }


    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @GetMapping(value = "/index")
    public ModelAndView index() {
        try {
            ModelAndView model = new ModelAndView();
            model.setViewName("index");
            return model;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping(value = "/games")
    public ModelAndView games(int page) {
        try {
            Pageable pageable = PageRequest.of(page - 1, 30);
            ModelAndView model = new ModelAndView();
            model.setViewName("games");

            Page<Game> listOfGames = gameService.getListOfGames(pageable);
            int totalPages = listOfGames.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addObject("pageNumbers", pageNumbers);
            }

            model.addObject("listOfGames", listOfGames);
            model.addObject("page", page);
            model.addObject("gamescriteria", new GamesCriteria());
            return model;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @PostMapping(value = "/games")
    public ModelAndView postGames(@ModelAttribute GamesCriteria gamesCriteria) {
        try {
            log.info("GamesCriteria {}", gamesCriteria);
            if (gamesCriteria != null) {
                GamesCriteria gamesCriteria1 = new GamesCriteria();
                GameSpecification gameSpecification = new GameSpecification(gamesCriteria1);
                List<Game> games = gameService.getListOfGamesWithCriteria(gameSpecification);


                if (!games.isEmpty()){
                    ModelAndView model = new ModelAndView();
                    model.setViewName("redirect:filtered");
                    model.addObject("games", games);
                    return model;
/*                    attributes.addFlashAttribute("games", games);
                    return new RedirectView("filtered");*/
                }
            }
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    @GetMapping(value = "/filtered")
    public ModelAndView getFiltered(){
        try{
            //TODO: Arreglar
            log.info("Llego al get filtered");
            ModelAndView model = new ModelAndView();
            model.setViewName("filtered");
            return model;
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
