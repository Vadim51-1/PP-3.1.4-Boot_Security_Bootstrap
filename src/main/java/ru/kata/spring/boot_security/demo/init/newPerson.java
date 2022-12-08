package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class newPerson implements CommandLineRunner {

    private final PeopleRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public newPerson(PeopleRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Person user = new Person();
        user.setUsername("User");
        user.setPassword("100");
        user.setYearOfBirth(1990);

        Person admin = new Person();
        admin.setUsername("Admin");
        admin.setPassword("100");
        admin.setYearOfBirth(2010);


        Role role = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        Set<Role> roles = new HashSet<>();

        roleRepository.save(role2);
        roles.add(role2);
        user.setRoles(roles);
        userRepository.save(user);

        roleRepository.save(role);
        roles.add(role);
        roles.remove(role2);
        admin.setRoles(roles);
        userRepository.save(admin);






    }

}
