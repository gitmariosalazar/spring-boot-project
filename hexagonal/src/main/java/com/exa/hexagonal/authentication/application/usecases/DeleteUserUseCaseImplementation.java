package com.exa.hexagonal.authentication.application.usecases;

import com.exa.hexagonal.authentication.domain.ports.input.DeleteUserInputPort;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;

public class DeleteUserUseCaseImplementation implements DeleteUserInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public DeleteUserUseCaseImplementation(UserRepositoryOutputPort userRepositoryOutputPort){
        this.userRepositoryOutputPort=userRepositoryOutputPort;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepositoryOutputPort.deleteById(id);
    }
}
