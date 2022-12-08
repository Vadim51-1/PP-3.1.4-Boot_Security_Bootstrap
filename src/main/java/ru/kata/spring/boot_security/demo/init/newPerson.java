package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;


import java.util.HashSet;
import java.util.Set;

@Component
public class newPerson implements CommandLineRunner {

    private final PeopleRepository peopleRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public newPerson(PeopleRepository peopleRepository,RoleRepository roleRepository) {
        this.peopleRepository = peopleRepository;
        this.roleRepository=roleRepository;

    }

    @Override
    public void run(String... args)  {


//        User user = new User();
//        user.setUsername("User");
//        user.setPassword("$2y$10$aHyB0C.gDd4p2U.jLBCS8ep6U4eh/CFSvZUCM8OTAcuAUb2h5Crgq");//100
//        user.setYearOfBirth(1990);
//
//        User admin = new User();
//        admin.setUsername("Admin");
//        admin.setPassword("$2y$10$aHyB0C.gDd4p2U.jLBCS8ep6U4eh/CFSvZUCM8OTAcuAUb2h5Crgq");//100
//        admin.setYearOfBirth(2010);
//
//
//        Role role = new Role("ROLE_ADMIN");
//        Role role2 = new Role("ROLE_USER");
//        Set<Role> roles = new HashSet<>();
//
//        roleRepository.save(role2);
//        roles.add(role2);
//        user.setRoles(roles);
//        peopleRepository.save(user);
//
//        roleRepository.save(role);
//        roles.add(role);
//        roles.remove(role2);
//        admin.setRoles(roles);
//        peopleRepository.save(admin);

        User admin = new User();
        admin.setUsername("Admin");
        admin.setPassword("$2y$10$aHyB0C.gDd4p2U.jLBCS8ep6U4eh/CFSvZUCM8OTAcuAUb2h5Crgq"); //100
        admin.setRoles(new String[]{"ROLE_ADMIN"});
        admin.setYearOfBirth(2010);
        peopleRepository.save(admin);

        User user = new User();
        user.setUsername("User");
        user.setPassword("$2y$10$cTd02aDowqkoLP/x8W.tuubXUsKlEcc06/EoDafv6g66SEwxxbYw2"); //200
        user.setYearOfBirth(1999);
        user.setRoles(new String[]{"ROLE_USER"});
        peopleRepository.save(user);




    }

}
