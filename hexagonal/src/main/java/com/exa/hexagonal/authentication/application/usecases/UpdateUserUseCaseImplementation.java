package com.exa.hexagonal.authentication.application.usecases;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.domain.ports.input.UpdateUserInputPort;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;

import java.util.Optional;

public class UpdateUserUseCaseImplementation implements UpdateUserInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public UpdateUserUseCaseImplementation(UserRepositoryOutputPort userRepositoryOutputPort){
        this.userRepositoryOutputPort=userRepositoryOutputPort;
    }

    @Override
    public Optional<User> updateUser(Long id, User userUpdated) {
        return userRepositoryOutputPort.update(id, userUpdated);
    }
}
