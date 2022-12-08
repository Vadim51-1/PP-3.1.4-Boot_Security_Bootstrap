package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.PersonDetailsService;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

import javax.validation.Valid;
import java.util.Arrays;


@Controller
@RequestMapping("/admin")
public class AdminControllers {

    private  final PersonDetailsService personDetailsService;

    private final PasswordEncoder passwordEncoder;



    private final RegistrationService registrationService;


    @Autowired
    public AdminControllers(RegistrationService registrationService,PersonDetailsService personDetailsService,PasswordEncoder passwordEncoder) {
        this.registrationService = registrationService;
        this.personDetailsService=personDetailsService;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping
    public String getFormPrintAllUsers(Model model) {
        model.addAttribute("people", registrationService.getAllUsers());
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUserData(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", registrationService.findUserById(id));
        return "showAdm";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        registrationService.delete(id);
        return "redirect:/admin";
    }



    @GetMapping("/{id}/edit")
    public String getViewForUpdateUser(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", personDetailsService.showUser(id));
        return "edit";
    }

    @PostMapping(value = "update/{id}")
   public   String update(@ModelAttribute("user") @Valid User user,
                         @PathVariable("id") int id) {
        registrationService.update(id, user);
        return "redirect:/admin";
    }


    @GetMapping("/users/new")
    public String getViewForNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/new";
    }

    @PostMapping("/users/newUsers")
    public String addUser(@ModelAttribute("user") User user,@RequestParam(value ="my_roles[]") String[] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        personDetailsService.createUser(user,roles);

        return "redirect:/admin";
    }


}