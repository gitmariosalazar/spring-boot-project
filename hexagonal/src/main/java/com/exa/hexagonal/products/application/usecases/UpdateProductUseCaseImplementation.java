package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.UpdateProductInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

import java.util.Optional;

public class UpdateProductUseCaseImplementation implements UpdateProductInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public UpdateProductUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public Optional<Product> updateProduct(String code, ProductRequest productRequest) {
        return productRepositoryOutputPort.updateProduct(code, productRequest);
    }
}
