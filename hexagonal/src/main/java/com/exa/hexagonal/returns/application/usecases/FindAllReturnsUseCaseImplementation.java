package com.exa.hexagonal.returns.application.usecases;

import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.domain.ports.input.FindAllReturnsInputPort;
import com.exa.hexagonal.returns.domain.ports.output.ReturnsRepositoryOutputPort;

import java.util.List;

public class FindAllReturnsUseCaseImplementation implements FindAllReturnsInputPort {

    private final ReturnsRepositoryOutputPort returnsRepositoryOutputPort;

    public FindAllReturnsUseCaseImplementation(ReturnsRepositoryOutputPort returnsRepositoryOutputPort){
        this.returnsRepositoryOutputPort=returnsRepositoryOutputPort;
    }

    @Override
    public List<Returns> findAllReturns() {
        return returnsRepositoryOutputPort.findAllReturns();
    }
}
