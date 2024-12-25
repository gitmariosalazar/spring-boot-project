package com.exa.hexagonal.selling.infrastructure.repositories.repository;

import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.infrastructure.model.entities.SellingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JPARepositorySelling extends JpaRepository<SellingEntity, Long> {
    List<SellingEntity> findAllBySellingDate(LocalDate sellingDate);
}
