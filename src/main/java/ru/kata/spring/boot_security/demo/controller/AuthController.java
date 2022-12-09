package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;


import ru.kata.spring.boot_security.demo.service.RegistrationServiceImpl;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationServiceImpl registrationServiceImpl;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserValidator userValidator, RegistrationServiceImpl registrationServiceImpl) {
        this.userValidator = userValidator;
        this.registrationServiceImpl = registrationServiceImpl;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";
        registrationServiceImpl.register(user);
        return "redirect:/auth/login";
    }


}