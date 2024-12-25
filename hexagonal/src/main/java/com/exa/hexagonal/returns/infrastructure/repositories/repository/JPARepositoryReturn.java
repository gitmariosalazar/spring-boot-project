package com.exa.hexagonal.returns.infrastructure.repositories.repository;

import com.exa.hexagonal.returns.infrastructure.model.entities.ReturnsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryReturn extends JpaRepository<ReturnsEntity, Long> {
}
