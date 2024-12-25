package com.exa.hexagonal.authentication.application.usecases;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.domain.ports.input.FindUserByEmailInputPort;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;

import java.util.Optional;

public class FindUserByEmailUseCaseImplementation implements FindUserByEmailInputPort {
    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public FindUserByEmailUseCaseImplementation(UserRepositoryOutputPort userRepositoryOutputPort){
        this.userRepositoryOutputPort=userRepositoryOutputPort;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryOutputPort.findByEmail(email);
    }
}
