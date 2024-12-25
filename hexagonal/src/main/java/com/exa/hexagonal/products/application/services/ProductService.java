package com.exa.hexagonal.products.application.services;

import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.input.*;

import java.util.List;
import java.util.Optional;

public class ProductService implements CreateProductInputPort, DeleteProductInputPort,
        FindAllProductsInputPort, FindProductByIdInputPort,
        FindProductByCodeInputPort, UpdateProductInputPort,
        FindProductWarningStockInputPort, FindAllUnpurchasedProductsInputPort {

    private final CreateProductInputPort createProductInputPort;
    private final DeleteProductInputPort deleteProductInputPort;
    private final FindAllProductsInputPort findAllProductsInputPort;
    private final FindProductByIdInputPort findProductByIdInputPort;
    private final FindProductByCodeInputPort findProductByCodeInputPort;
    private final UpdateProductInputPort updateProductInputPort;
    private final FindProductWarningStockInputPort findProductWarningStockInputPort;
    private final FindAllUnpurchasedProductsInputPort findAllProductsUnpurchasedInputPort;

    public ProductService(
            CreateProductInputPort createProductInputPort,
            DeleteProductInputPort deleteProductInputPort,
            FindAllProductsInputPort findAllProductsInputPort,
            FindProductByIdInputPort findProductByIdInputPort,
            FindProductByCodeInputPort findProductByCodeInputPort,
            UpdateProductInputPort updateProductInputPort,
            FindProductWarningStockInputPort findProductWarningStockInputPort,
            FindAllUnpurchasedProductsInputPort findAllProductsUnpurchasedInputPort
    ){
        this.createProductInputPort=createProductInputPort;
        this.deleteProductInputPort=deleteProductInputPort;
        this.findAllProductsInputPort=findAllProductsInputPort;
        this.findProductByIdInputPort=findProductByIdInputPort;
        this.findProductByCodeInputPort=findProductByCodeInputPort;
        this.updateProductInputPort=updateProductInputPort;
        this.findProductWarningStockInputPort=findProductWarningStockInputPort;
        this.findAllProductsUnpurchasedInputPort=findAllProductsUnpurchasedInputPort;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return createProductInputPort.createProduct(productRequest);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return deleteProductInputPort.deleteProduct(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return findAllProductsInputPort.findAllProducts();
    }

    @Override
    public Optional<Product> findProductByCode(String code) {
        return findProductByCodeInputPort.findProductByCode(code);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return findProductByIdInputPort.findProductById(id);
    }

    @Override
    public Optional<Product> updateProduct(String code,ProductRequest productRequest) {
        return updateProductInputPort.updateProduct(code, productRequest);
    }

    @Override
    public List<Product> findAllProductWarningStock(Integer quantityLimit) {
        return findProductWarningStockInputPort.findAllProductWarningStock(quantityLimit);
    }

    @Override
    public List<Product> findUnpurchasedProducts() {
        return findAllProductsUnpurchasedInputPort.findUnpurchasedProducts();
    }
}
