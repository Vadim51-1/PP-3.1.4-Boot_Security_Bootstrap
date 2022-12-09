package ru.kata.spring.boot_security.demo.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

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
    public void creationOfDefaultUsers() {
        var role = new Role("ROLE_ADMIN");
        var role2 = new Role("ROLE_USER");
        roleRepository.saveAll(Set.of(role, role2));
        createUser("Admin", 1970, passwordEncoder.encode("hochySpat"), Set.of(role));
        createUser("User", 2000, passwordEncoder.encode("hochyEst"), Set.of(role2));
    }

    private void createUser(String name, int age, String password, Set<Role> role) {
        User user = new User(name, age, password, role);
        userRepository.save(user);
    }
}