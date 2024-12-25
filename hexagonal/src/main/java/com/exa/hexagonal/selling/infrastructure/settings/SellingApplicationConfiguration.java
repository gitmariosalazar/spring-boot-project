package com.exa.hexagonal.selling.infrastructure.settings;

import com.exa.hexagonal.products.application.usecases.FindAllProductsUseCaseImplementation;
import com.exa.hexagonal.selling.application.services.SellingService;
import com.exa.hexagonal.selling.application.usecases.*;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;
import com.exa.hexagonal.selling.infrastructure.mappers.SellingMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SellingApplicationConfiguration {
    @Bean
    public SellingService sellingService(SellingRepositoryOutputPort sellingRepositoryOutputPort){
        return new SellingService(
                new CreateSellingUseCaseImplementation(sellingRepositoryOutputPort),
                new FindSellingByIdUseCaseImplementation(sellingRepositoryOutputPort),
                new UpdateSellingUseCaseImplementation(sellingRepositoryOutputPort),
                new FindAllSellingUseCaseImplementation(sellingRepositoryOutputPort),
                new DeleteSellingUseCaseImplementation(sellingRepositoryOutputPort),
                new FindAllSellingByDateUseCaseImplementation(sellingRepositoryOutputPort)
        );
    }

    @Bean
    SellingMapper sellingMapper(){
        return new SellingMapper();
    }
}
