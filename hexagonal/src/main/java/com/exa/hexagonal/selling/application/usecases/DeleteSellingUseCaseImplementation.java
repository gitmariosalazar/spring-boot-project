package com.exa.hexagonal.selling.application.usecases;

import com.exa.hexagonal.selling.domain.ports.input.DeleteSellingInputPort;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;

public class DeleteSellingUseCaseImplementation implements DeleteSellingInputPort {

    private final SellingRepositoryOutputPort sellingRepositoryOutputPort;

    public DeleteSellingUseCaseImplementation(SellingRepositoryOutputPort sellingRepositoryOutputPort){
        this.sellingRepositoryOutputPort=sellingRepositoryOutputPort;
    }

    @Override
    public boolean deleteSelling(Long id) {
        return sellingRepositoryOutputPort.deleteSelling(id);
    }
}
