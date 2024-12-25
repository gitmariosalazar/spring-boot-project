package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.FindProductByCodeInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

import java.util.Optional;

public class FindProductByCodeUseCaseImplementation implements FindProductByCodeInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public FindProductByCodeUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public Optional<Product> findProductByCode(String code) {
        return productRepositoryOutputPort.findProductByCode(code);
    }
}
