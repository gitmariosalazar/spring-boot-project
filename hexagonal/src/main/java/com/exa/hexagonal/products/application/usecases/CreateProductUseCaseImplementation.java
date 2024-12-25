package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.CreateProductInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

public class CreateProductUseCaseImplementation implements CreateProductInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public CreateProductUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return productRepositoryOutputPort.createProduct(productRequest);
    }
}
