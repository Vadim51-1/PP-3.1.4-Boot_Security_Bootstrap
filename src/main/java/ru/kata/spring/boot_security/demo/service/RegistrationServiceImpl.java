package ru.kata.spring.boot_security.demo.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.repositorу.RoleRepository;
import ru.kata.spring.boot_security.demo.repositorу.UserRepository;

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

        var role = new Role("ROLE_USER");
        roleRepository.save(role);
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        var roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }
    @Override
    public User findUserById(Integer userId) {

        var userFromDb = userRepository.findById(userId);
        if (userFromDb.isPresent()) {
            return  userFromDb.get();
        } else throw new UsernameNotFoundException("User not found!");
    }
}