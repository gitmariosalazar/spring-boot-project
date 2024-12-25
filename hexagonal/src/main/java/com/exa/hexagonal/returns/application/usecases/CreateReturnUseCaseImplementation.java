package com.exa.hexagonal.returns.application.usecases;

import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.domain.ports.input.CreateReturnInputPort;
import com.exa.hexagonal.returns.domain.ports.output.ReturnsRepositoryOutputPort;

public class CreateReturnUseCaseImplementation implements CreateReturnInputPort {

    private final ReturnsRepositoryOutputPort returnsRepositoryOutputPort;

    public CreateReturnUseCaseImplementation(ReturnsRepositoryOutputPort returnsRepositoryOutputPort){
        this.returnsRepositoryOutputPort=returnsRepositoryOutputPort;
    }

    @Override
    public Returns createReturn(Returns returns) {
        return returnsRepositoryOutputPort.createReturn(returns);
    }
}
