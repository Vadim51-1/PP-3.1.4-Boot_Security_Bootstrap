package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;

public interface RegistrationService {

    void register(User user);

    User findUserById(Integer userId);

    HashSet<Role> getNewRol();

}