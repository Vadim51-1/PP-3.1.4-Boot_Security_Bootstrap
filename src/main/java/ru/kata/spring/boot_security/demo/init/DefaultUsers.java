package ru.kata.spring.boot_security.demo.init;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.repositorу.UserRepository;
import ru.kata.spring.boot_security.demo.repositorу.RoleRepository;


import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DefaultUsers {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUsers(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
        userRepository.save(user);
    }

}