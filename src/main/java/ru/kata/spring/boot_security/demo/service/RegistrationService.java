package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional(readOnly = true)
@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final PeopleRepository peopleRepository;

    private final RoleRepository roleRepository;


    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, PeopleRepository peopleRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.peopleRepository = peopleRepository;
        this.roleRepository = roleRepository;

    }

    public void register(User user) {

        Role role = new Role("ROLE_USER");
        roleRepository.save(role);
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        peopleRepository.save(user);
    }


    public List<User> getAllUsers() {
        return peopleRepository.findAll();
    }

    public User findUserById(Integer userId) {
        Optional<User> userFromDb = peopleRepository.findById(userId);
        return userFromDb.orElse(null);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        peopleRepository.save(updatedUser);
    }




}