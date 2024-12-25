package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.products.domain.ports.input.DeleteProductInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

public class DeleteProductUseCaseImplementation implements DeleteProductInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public DeleteProductUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepositoryOutputPort.deleteProduct(id);
    }
}
