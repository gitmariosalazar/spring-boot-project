package com.exa.hexagonal.products.domain.ports.output;

import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryOutputPort {
    Product createProduct(ProductRequest productRequest);
    Optional<Product> updateProduct(String code,ProductRequest productRequest);
    Optional<Product> findProductById(Long id);
    Optional<Product> findProductByCode(String code);
    boolean deleteProduct(Long id);
    List<Product> findAllProducts();
    List<Product> findAllProductWarningStock(Integer quantityLimit);
    List<Product> findAllProductsUnpurchased();
}
