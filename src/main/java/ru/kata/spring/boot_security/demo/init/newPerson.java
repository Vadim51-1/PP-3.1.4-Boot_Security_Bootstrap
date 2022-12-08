package ru.kata.spring.boot_security.demo.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;


import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class newPerson {

    private final PeopleRepository peopleRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public newPerson(PeopleRepository peopleRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        var role = new Role("ROLE_ADMIN");
        var role2 = new Role("ROLE_USER");
        roleRepository.saveAll(Set.of(role, role2));
        createUser("Admin", 2010, passwordEncoder.encode("200"), Set.of(role));
        createUser("User", 2012 ,passwordEncoder.encode("100"), Set.of(role2));



    }

    private void createUser(String name, int age, String password, Set<Role> role) {
        User user = new User(name, age, password, role);
        peopleRepository.save(user);
    }

}
