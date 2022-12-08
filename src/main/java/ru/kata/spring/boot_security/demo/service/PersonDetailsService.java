package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    private  final RoleRepository roleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository, RoleRepository roleRepository) {
        this.peopleRepository = peopleRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = peopleRepository.findByUsername(s);

        if (user ==null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    public User showUser(Integer id) {
        return peopleRepository.findById(id).get();
    }
    @Transactional
    public void createUser(User user, String[] rol) {
        Set<Role> byRoleIn = roleRepository.findByRoleIn(Arrays.asList(rol));
        user.setRoles(byRoleIn);
        peopleRepository.save(user);
    }




}