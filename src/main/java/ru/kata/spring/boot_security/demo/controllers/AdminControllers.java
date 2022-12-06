package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.service.RegistrationService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminControllers {


    private final RegistrationService registrationService;


    @Autowired
    public AdminControllers(RegistrationService registrationService) {
        this.registrationService = registrationService;
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
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", registrationService.findUserById(id));
        return "/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/edit";

        registrationService.update(id, person);
        return "redirect:/admin";
    }


}