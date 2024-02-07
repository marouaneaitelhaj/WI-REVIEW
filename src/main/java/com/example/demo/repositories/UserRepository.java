package com.example.demo.repositories;

import com.example.demo.entities.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<DbUser, UUID> {
    Optional<DbUser> findByUsername(String username);
}
