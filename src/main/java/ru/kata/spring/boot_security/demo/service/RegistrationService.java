package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


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

    public void register(Person person) {

        Role role = new Role("ROLE_USER");
        roleRepository.save(role);
        person.setPassword((passwordEncoder.encode(person.getPassword())));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        person.setRoles(roles);

        peopleRepository.save(person);
    }

    @Transactional(readOnly = true)
    public List<Person> getAllUsers() {
        return peopleRepository.findAll();
    }

    public Person findUserById(Integer userId) {
        Optional<Person> userFromDb = peopleRepository.findById(userId);
        return userFromDb.orElse(null);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

//    public Optional<Person> findByUsername(String username) {
//        return peopleRepository.findByUsername(username);
//    }


}