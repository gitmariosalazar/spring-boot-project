package com.exa.hexagonal.authentication.application.usecases;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.domain.ports.input.GetUsersInputPort;
import com.exa.hexagonal.authentication.domain.ports.output.UserRepositoryOutputPort;

import java.util.List;

public class GetUsersUseCaseImplementation implements GetUsersInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public GetUsersUseCaseImplementation(UserRepositoryOutputPort userRepositoryOutputPort){
        this.userRepositoryOutputPort=userRepositoryOutputPort;
    }

    @Override
    public List<User> getUsers() {
        return userRepositoryOutputPort.findAll();
    }
}
