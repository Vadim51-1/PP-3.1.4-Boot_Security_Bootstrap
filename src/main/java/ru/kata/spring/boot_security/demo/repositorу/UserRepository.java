package ru.kata.spring.boot_security.demo.repositorу;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}