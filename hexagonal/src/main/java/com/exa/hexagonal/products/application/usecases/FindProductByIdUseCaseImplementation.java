package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.FindProductByIdInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

import java.util.Optional;

public class FindProductByIdUseCaseImplementation implements FindProductByIdInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public FindProductByIdUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepositoryOutputPort.findProductById(id);
    }
}
