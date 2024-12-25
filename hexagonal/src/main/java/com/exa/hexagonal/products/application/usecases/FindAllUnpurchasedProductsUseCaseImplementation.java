package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.FindAllUnpurchasedProductsInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

import java.util.List;

public class FindAllUnpurchasedProductsUseCaseImplementation implements FindAllUnpurchasedProductsInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public FindAllUnpurchasedProductsUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public List<Product> findUnpurchasedProducts() {
        return productRepositoryOutputPort.findAllProductsUnpurchased();
    }
}
