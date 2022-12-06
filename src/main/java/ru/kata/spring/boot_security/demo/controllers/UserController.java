package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

import java.security.Principal;


@Controller
public class UserController {

    private final RegistrationService registrationService;


    @Autowired
    public UserController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

//    @GetMapping("/user")

//    public String pageForAuthenticatedUsers(Principal principal) {
//        Person person = p.findByUsername(principal.getName());
//        return "secured part of web service: " + person.getUsername() + " " + person.getYearOfBirth();
//    }
}
