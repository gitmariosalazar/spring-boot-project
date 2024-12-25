package com.exa.hexagonal.authentication.domain.ports.input;

import com.exa.hexagonal.authentication.domain.model.User;

import java.util.Optional;

public interface UpdateUserInputPort {
    Optional<User> updateUser(Long id, User userUpdated);
}
