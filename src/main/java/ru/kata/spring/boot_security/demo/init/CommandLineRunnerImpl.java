//package ru.kata.spring.boot_security.demo.init;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.core.parameters.P;
//import org.springframework.stereotype.Component;
//
//import ru.kata.spring.boot_security.demo.models.Person;
//import ru.kata.spring.boot_security.demo.models.Role;
//import ru.kata.spring.boot_security.demo.repositories.PeopleRepository;
//import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
//
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class CommandLineRunnerImpl implements CommandLineRunner {
//
//    private final PeopleRepository userRepository;
//
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public CommandLineRunnerImpl(PeopleRepository userRepository,RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository=roleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//
//
//        Role role = new Role("ROLE_ADMIN");
//        roleRepository.save(role);
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        Person admin = new Person();
//        admin.setUsername("Admin");
//        admin.setPassword("100"); //100
//        admin.setYearOfBirth(2010);
//        admin.setRoles(roles);
//        userRepository.save(admin);
//
//
//
//        Role role2 = new Role("ROLE_USER");
//        roleRepository.save(role2);
//        Set<Role> roles2 = new HashSet<>();
//        roles.add(role2);
//        Person admin2 = new Person();
//        admin2.setUsername("User");
//        admin2.setPassword("100"); //100
//        admin2.setYearOfBirth(1990);
//        admin2.setRoles(roles2);
//        userRepository.save(admin2);
//
//
//    }
//}


