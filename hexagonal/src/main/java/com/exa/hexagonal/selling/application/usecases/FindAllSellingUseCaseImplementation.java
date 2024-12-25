package com.exa.hexagonal.selling.application.usecases;

import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.ports.input.FindAllSellingInputPort;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;

import java.util.List;

public class FindAllSellingUseCaseImplementation implements FindAllSellingInputPort {
    private final SellingRepositoryOutputPort sellingRepositoryOutputPort;

    public FindAllSellingUseCaseImplementation(SellingRepositoryOutputPort sellingRepositoryOutputPort){
        this.sellingRepositoryOutputPort=sellingRepositoryOutputPort;
    }

    @Override
    public List<Selling> findAllSelling() {
        return sellingRepositoryOutputPort.findAllSelling();
    }
}
