package com.exa.hexagonal.authentication.application.usecases;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.domain.ports.input.GetUserInputPort;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;

import java.util.Optional;

public class GetUserUseCaseImplementation implements GetUserInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public GetUserUseCaseImplementation(UserRepositoryOutputPort userRepositoryOutputPort){
        this.userRepositoryOutputPort=userRepositoryOutputPort;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepositoryOutputPort.findById(id);
    }
}
