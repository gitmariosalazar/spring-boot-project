package com.exa.hexagonal.authentication.infrastructure.repositories.repository;

import com.exa.hexagonal.authentication.infrastructure.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPARepositoryUser extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
