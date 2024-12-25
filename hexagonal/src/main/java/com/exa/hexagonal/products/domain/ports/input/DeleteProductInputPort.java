package com.exa.hexagonal.products.domain.ports.input;

public interface DeleteProductInputPort {
    boolean deleteProduct(Long id);
}
