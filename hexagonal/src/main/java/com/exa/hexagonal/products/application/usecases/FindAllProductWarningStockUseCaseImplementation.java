package com.exa.hexagonal.products.application.usecases;

import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.FindProductWarningStockInputPort;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;

import java.util.List;

public class FindAllProductWarningStockUseCaseImplementation implements FindProductWarningStockInputPort {

    private final ProductRepositoryOutputPort productRepositoryOutputPort;

    public FindAllProductWarningStockUseCaseImplementation(ProductRepositoryOutputPort productRepositoryOutputPort){
        this.productRepositoryOutputPort=productRepositoryOutputPort;
    }

    @Override
    public List<Product> findAllProductWarningStock(Integer quantityLimit) {
        return productRepositoryOutputPort.findAllProductWarningStock(quantityLimit);
    }
}
