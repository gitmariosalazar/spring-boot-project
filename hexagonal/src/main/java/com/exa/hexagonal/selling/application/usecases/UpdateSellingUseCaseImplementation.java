package com.exa.hexagonal.selling.application.usecases;

import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.ports.input.UpdateSellingInputPort;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;

import java.util.Optional;

public class UpdateSellingUseCaseImplementation implements UpdateSellingInputPort {

    private final SellingRepositoryOutputPort sellingRepositoryOutputPort;

    public UpdateSellingUseCaseImplementation(SellingRepositoryOutputPort sellingRepositoryOutputPort){
        this.sellingRepositoryOutputPort=sellingRepositoryOutputPort;
    }

    @Override
    public Optional<Selling> updateSelling(Long id, Selling selling) {
        return sellingRepositoryOutputPort.updateSelling(id, selling);
    }
}
