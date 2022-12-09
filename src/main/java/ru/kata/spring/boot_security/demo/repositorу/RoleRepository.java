package ru.kata.spring.boot_security.demo.repositorу;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Integer id);

    Set<Role> findByRoleIn(List<String> role);
}