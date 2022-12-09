package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class RoleServiceI implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceI(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Set<Role> findByRoleIn(List<String> role) {
        return roleRepository.findByRoleIn(role);
    }
}
