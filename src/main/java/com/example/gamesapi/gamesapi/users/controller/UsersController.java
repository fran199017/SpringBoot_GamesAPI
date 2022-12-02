package com.example.gamesapi.gamesapi.users.controller;

import com.example.gamesapi.gamesapi.games.controller.PublicController;
import com.example.gamesapi.gamesapi.users.model.Users;
import com.example.gamesapi.gamesapi.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Users user = userRepository.findByEmail(email);
            List<Users> listUsers = userRepository.findAll();
            model.addAttribute("listUsers", listUsers);
            model.addAttribute("user", user);
            return "users";
        }catch (Exception e){
            log.error("Error {}", e.getMessage());
            return "index";
        }
    }

    @DeleteMapping("/users")
    public ModelAndView deleteUser(String id) {
        int intId = Integer.parseInt(id);
        Optional<Users> userOpt = userRepository.findById(intId);
        if (userOpt.isPresent()){
            Users user = userOpt.get();
            userRepository.delete(user);
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("users");
        return model;
    }
}
