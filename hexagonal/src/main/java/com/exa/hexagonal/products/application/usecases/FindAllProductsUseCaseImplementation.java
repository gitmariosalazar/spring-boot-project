package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.FindAllProductsInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

import java.util.List;

public class FindAllProductsUseCaseImplementation implements FindAllProductsInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public FindAllProductsUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepositoryOutputPort.findAllProducts();
    }
}
