package com.example.gamesapi.gamesapi.games.controller;

import com.example.gamesapi.gamesapi.games.model.Game;
import com.example.gamesapi.gamesapi.games.model.GameSpecification;
import com.example.gamesapi.gamesapi.games.model.GamesCriteria;
import com.example.gamesapi.gamesapi.games.services.GameService;
import com.example.gamesapi.gamesapi.users.model.Users;
import com.example.gamesapi.gamesapi.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/public")
public class PublicController {

    GameService gameService;
    UserRepository userRepository;

    @Autowired
    public PublicController(GameService gameService, UserRepository userRepository) {
        this.gameService = gameService;
        this.userRepository = userRepository;
    }



    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    @GetMapping(value = "/games")
    public ModelAndView games(int page, String key, String value ){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Users user = userRepository.findByEmail(email);

            GamesCriteria gamesCriteria = null;
            Pageable pageable;
            Page<Game> listOfGames = null;
            int totalPages = 0;

            if (key!= null && value != null){
                gamesCriteria = new GamesCriteria();
                gamesCriteria.setKey(key);
                gamesCriteria.setValue(value);
            }

            ModelAndView model = new ModelAndView();
            model.setViewName("games");

            if (gamesCriteria != null){
                GameSpecification gameSpecification = new GameSpecification(gamesCriteria);
                List<Game> games = gameService.getListOfGamesWithCriteria(gameSpecification);
                if (!games.isEmpty()){
                    pageable = PageRequest.of(0, games.size());
                    listOfGames = new PageImpl<>(games,pageable, games.size());
                    totalPages = listOfGames.getTotalPages();
                }
            }else{
                pageable = PageRequest.of(page -1,30);
                listOfGames = gameService.getListOfGames(pageable);
                totalPages = listOfGames.getTotalPages();
            }


            if (totalPages > 0){
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addObject("pageNumbers", pageNumbers);
            }

            model.addObject("listOfGames", listOfGames);
            model.addObject("page", page);
            model.addObject("user", user);
            return model;
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }

    @GetMapping(value = "/game")
    public ModelAndView game(int id){
        try{
            log.info("id {}", id);
            Game game = gameService.findById(id);
            ModelAndView model = new ModelAndView();
            model.setViewName("game");
            if (game != null){
                model.addObject("game", game);
                return model;
            }
            return model;
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }

    //TODO: Sacar rol admin en list users, borrar user como admin, controlar error de registro con mismo email.

    @DeleteMapping(value = "/game")
    public ModelAndView deleteGame(String id){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Users user = userRepository.findByEmail(email);

            if (user.getRole().equals("admin")){
                int idInt = Integer.parseInt(id);
                gameService.deleteGame(idInt);
            }

            ModelAndView model = new ModelAndView();
            model.setViewName("game");
            return model;
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
