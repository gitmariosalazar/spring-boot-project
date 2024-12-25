package com.exa.hexagonal.products.domain.ports.input;

import com.exa.hexagonal.products.domain.model.Product;

import java.util.List;

public interface FindProductWarningStockInputPort {
    List<Product> findAllProductWarningStock(Integer quantityLimit);
}
