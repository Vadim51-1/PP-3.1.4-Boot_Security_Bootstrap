package ru.kata.spring.boot_security.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositorу.UserRepository;
import ru.kata.spring.boot_security.demo.repositorу.RoleRepository;


import java.util.HashSet;


@Transactional(readOnly = true)
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    @Autowired
    public RegistrationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }
    @Transactional
    public void register(User user) {

        Role role = new Role("ROLE_USER");
        roleRepository.save(role);
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        var roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public HashSet<Role> getNewRol(){
        var roles = new HashSet<Role>();
        roles.add(roleRepository.save(new Role("ROLE_USER")));
        return roles;
    }

    public User findUserById(Integer userId) {
        var userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(null);
    }

}