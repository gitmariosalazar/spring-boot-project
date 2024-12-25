package com.exa.hexagonal.products.domain.ports.input;

import com.exa.hexagonal.products.domain.model.Product;

import java.util.Optional;

public interface FindProductByCodeInputPort {
    Optional<Product> findProductByCode(String code);
}
