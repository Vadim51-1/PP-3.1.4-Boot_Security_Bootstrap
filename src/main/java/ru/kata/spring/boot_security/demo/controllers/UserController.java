package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.service.PersonDetailsService;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

import java.security.Principal;
import java.util.Optional;


@Controller
public class UserController {

    private final PersonDetailsService personDetailsService;

    private final RegistrationService registrationService;


    @Autowired
    public UserController(RegistrationService registrationService, PersonDetailsService personDetailsService) {
        this.registrationService = registrationService;
        this.personDetailsService = personDetailsService;
    }
    @GetMapping("/user")
    public String showUserData(Principal principal, Model model)  {
       Person person = personDetailsService.findByUsername(principal.getName());
        model.addAttribute("user", personDetailsService.showUser(person.getId()));
        return "/user";
    }


}