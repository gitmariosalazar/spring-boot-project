package com.exa.hexagonal.authentication.application.usecases;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.domain.ports.input.CreateUserInputPort;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;

public class CreateUserUseCaseImplementation implements CreateUserInputPort {
    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public CreateUserUseCaseImplementation(UserRepositoryOutputPort userRepositoryOutputPort){
        this.userRepositoryOutputPort=userRepositoryOutputPort;
    }

    @Override
    public User createUser(User user) {
        return userRepositoryOutputPort.save(user);
    }
}
