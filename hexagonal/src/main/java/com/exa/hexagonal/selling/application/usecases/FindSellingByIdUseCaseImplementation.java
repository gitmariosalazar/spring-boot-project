package com.exa.hexagonal.selling.application.usecases;

import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.ports.input.FindSellingByIdInputPort;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;

import java.util.Optional;

public class FindSellingByIdUseCaseImplementation implements FindSellingByIdInputPort {
    private final SellingRepositoryOutputPort sellingRepositoryOutputPort;

    public FindSellingByIdUseCaseImplementation(SellingRepositoryOutputPort sellingRepositoryOutputPort){
        this.sellingRepositoryOutputPort=sellingRepositoryOutputPort;
    }

    @Override
    public Optional<Selling> findSellingById(Long id) {
        return sellingRepositoryOutputPort.findSellingById(id);
    }
}
