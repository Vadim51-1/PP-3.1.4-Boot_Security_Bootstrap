package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

public interface RegistrationService {

    void register(User user);

    User findUserById(Integer userId);

}
