package com.exa.hexagonal.products.domain.ports.input;

import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.model.Product;

public interface CreateProductInputPort {
    Product createProduct(ProductRequest productRequest);
}
