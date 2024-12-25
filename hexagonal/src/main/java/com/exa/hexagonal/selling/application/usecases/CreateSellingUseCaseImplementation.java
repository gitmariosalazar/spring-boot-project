package com.exa.hexagonal.selling.application.usecases;

import com.exa.hexagonal.selling.domain.dto.request.SellingRequest;
import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.ports.input.CreateSellingInputPort;
import com.exa.hexagonal.selling.domain.ports.input.FindSellingByIdInputPort;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;

import java.util.Optional;

public class CreateSellingUseCaseImplementation implements CreateSellingInputPort {

    private final SellingRepositoryOutputPort sellingRepositoryOutputPort;

    public CreateSellingUseCaseImplementation(SellingRepositoryOutputPort sellingRepositoryOutputPort){
        this.sellingRepositoryOutputPort=sellingRepositoryOutputPort;
    }

    @Override
    public Selling createSelling(Selling selling) {
        return sellingRepositoryOutputPort.createSelling(selling);
    }

}
