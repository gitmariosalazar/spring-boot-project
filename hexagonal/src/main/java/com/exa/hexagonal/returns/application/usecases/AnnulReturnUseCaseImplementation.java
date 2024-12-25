package com.exa.hexagonal.returns.application.usecases;

import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.domain.ports.input.AnnulReturnInputPort;
import com.exa.hexagonal.returns.domain.ports.output.ReturnsRepositoryOutputPort;

import java.util.Optional;

public class AnnulReturnUseCaseImplementation implements AnnulReturnInputPort {

    private final ReturnsRepositoryOutputPort returnsRepositoryOutputPort;

    public AnnulReturnUseCaseImplementation(ReturnsRepositoryOutputPort returnsRepositoryOutputPort){
        this.returnsRepositoryOutputPort=returnsRepositoryOutputPort;
    }

    @Override
    public Optional<Returns> annulReturn(Long id, String status) {
        return returnsRepositoryOutputPort.annulReturn(id, status);
    }
}
