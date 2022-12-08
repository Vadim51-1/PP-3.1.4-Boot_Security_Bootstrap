package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.PersonDetailsService;


import java.security.Principal;


@Controller
public class UserController {

    private final PersonDetailsService personDetailsService;




    @Autowired
    public UserController( PersonDetailsService personDetailsService) {

        this.personDetailsService = personDetailsService;
    }
    @GetMapping("/user")
    public String showUserData(Principal principal, Model model)  {
       User user = personDetailsService.findByUsername(principal.getName());
        model.addAttribute("user", personDetailsService.showUser(user.getId()));
        return "/user";
    }


}