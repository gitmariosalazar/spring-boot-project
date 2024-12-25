package com.exa.hexagonal.selling.domain.ports.input;

import com.exa.hexagonal.selling.domain.model.Selling;

import java.util.Optional;

public interface UpdateSellingInputPort {
    Optional<Selling> updateSelling(Long id, Selling selling);
}
