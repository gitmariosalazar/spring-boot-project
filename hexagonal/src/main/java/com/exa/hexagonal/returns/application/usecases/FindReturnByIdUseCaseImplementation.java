package com.exa.hexagonal.returns.application.usecases;

import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.domain.ports.input.FindReturnByIdInputPort;
import com.exa.hexagonal.returns.domain.ports.output.ReturnsRepositoryOutputPort;

import java.util.Optional;

public class FindReturnByIdUseCaseImplementation implements FindReturnByIdInputPort {

    private final ReturnsRepositoryOutputPort returnsRepositoryOutputPort;

    public FindReturnByIdUseCaseImplementation(ReturnsRepositoryOutputPort returnsRepositoryOutputPort){
        this.returnsRepositoryOutputPort=returnsRepositoryOutputPort;
    }

    @Override
    public Optional<Returns> findReturnById(Long id) {
        return returnsRepositoryOutputPort.findReturnById(id);
    }
}
