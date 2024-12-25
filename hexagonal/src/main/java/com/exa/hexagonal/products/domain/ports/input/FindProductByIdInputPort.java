package com.exa.hexagonal.products.domain.ports.input;

import com.exa.hexagonal.products.domain.model.Product;

import java.util.Optional;

public interface FindProductByIdInputPort {
    Optional<Product> findProductById(Long id);
}
