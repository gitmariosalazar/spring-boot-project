package com.exa.hexagonal.products.domain.ports.input;

import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.model.Product;

import java.util.Optional;

public interface UpdateProductInputPort {
    Optional<Product> updateProduct(String code, ProductRequest productRequest);
}
