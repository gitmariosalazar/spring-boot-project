package com.exa.hexagonal.authentication.domain.ports.input;

import com.exa.hexagonal.authentication.domain.model.User;

import java.util.Optional;

public interface FindUserByEmailInputPort {
    Optional<User> findByEmail(String email);
}
