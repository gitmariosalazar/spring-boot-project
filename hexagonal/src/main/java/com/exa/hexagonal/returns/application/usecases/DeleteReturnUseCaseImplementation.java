package com.exa.hexagonal.returns.application.usecases;

import com.exa.hexagonal.returns.domain.ports.input.DeleteReturnInputPort;
import com.exa.hexagonal.returns.domain.ports.output.ReturnsRepositoryOutputPort;

public class DeleteReturnUseCaseImplementation implements DeleteReturnInputPort {

    private final ReturnsRepositoryOutputPort returnsRepositoryOutputPort;

    public DeleteReturnUseCaseImplementation(ReturnsRepositoryOutputPort returnsRepositoryOutputPort){
        this.returnsRepositoryOutputPort=returnsRepositoryOutputPort;
    }

    @Override
    public boolean deleteReturn(Long id) {
        return returnsRepositoryOutputPort.deleteReturn(id);
    }
}
