package com.exa.hexagonal.authentication.application.services;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.authentication.domain.ports.input.*;

import java.util.List;
import java.util.Optional;

public class UserService implements CreateUserInputPort, GetUserInputPort, GetUsersInputPort, UpdateUserInputPort, DeleteUserInputPort, FindUserByEmailInputPort {
    private final CreateUserInputPort createUserInputPort;
    private final GetUsersInputPort getUsersInputPort;
    private final GetUserInputPort getUserInputPort;
    private final UpdateUserInputPort updateUserInputPort;
    private final DeleteUserInputPort deleteUserInputPort;
    private final FindUserByEmailInputPort findUserByEmailInputPort;

    public UserService(CreateUserInputPort createUserInputPort,
                       GetUsersInputPort getUsersInputPort,
                       GetUserInputPort getUserInputPort,
                       UpdateUserInputPort updateUserInputPort,
                       DeleteUserInputPort deleteUserInputPort,
                       FindUserByEmailInputPort findUserByEmailInputPort){
        this.createUserInputPort=createUserInputPort;
        this.getUsersInputPort=getUsersInputPort;
        this.getUserInputPort=getUserInputPort;
        this.updateUserInputPort=updateUserInputPort;
        this.deleteUserInputPort=deleteUserInputPort;
        this.findUserByEmailInputPort =findUserByEmailInputPort;
    }

    @Override
    public User createUser(User user) {
        return createUserInputPort.createUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return deleteUserInputPort.deleteUser(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return getUserInputPort.getUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return getUsersInputPort.getUsers();
    }

    @Override
    public Optional<User> updateUser(Long id, User userUpdated) {
        return updateUserInputPort.updateUser(id, userUpdated);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return findUserByEmailInputPort.findByEmail(email);
    }
}
