package com.exa.hexagonal.selling.domain.ports.input;

import com.exa.hexagonal.selling.domain.model.Selling;

import java.util.Optional;

public interface FindSellingByIdInputPort {
    Optional<Selling> findSellingById(Long id);
}
