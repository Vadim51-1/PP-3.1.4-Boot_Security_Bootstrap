package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface RoleService {

    Optional<Role> findById(Long id);

    Set<Role> findByRoleIn(List<String> role);
}
