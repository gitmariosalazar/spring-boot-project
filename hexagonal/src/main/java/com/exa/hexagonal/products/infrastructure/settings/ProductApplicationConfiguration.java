package com.exa.hexagonal.products.infrastructure.settings;

import com.exa.hexagonal.products.application.services.ProductService;
import com.exa.hexagonal.products.application.usecases.*;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;
import com.exa.hexagonal.products.infrastructure.mappers.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductApplicationConfiguration {
    @Bean
    public ProductService productService(ProductRepositoryOutputPort productRepositoryOutputPort){
        return new ProductService(
                new CreateProductUseCaseImplementation(productRepositoryOutputPort),
                new DeleteProductUseCaseImplementation(productRepositoryOutputPort),
                new FindAllProductsUseCaseImplementation(productRepositoryOutputPort),
                new FindProductByIdUseCaseImplementation(productRepositoryOutputPort),
                new FindProductByCodeUseCaseImplementation(productRepositoryOutputPort),
                new UpdateProductUseCaseImplementation(productRepositoryOutputPort),
                new FindAllProductWarningStockUseCaseImplementation(productRepositoryOutputPort),
                new FindAllUnpurchasedProductsUseCaseImplementation(productRepositoryOutputPort)
        );
    }

    @Bean
    ProductMapper productMapper(){return new ProductMapper();}
}
