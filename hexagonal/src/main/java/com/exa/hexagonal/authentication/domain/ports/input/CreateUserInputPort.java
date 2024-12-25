package com.exa.hexagonal.authentication.domain.ports.input;

import com.exa.hexagonal.authentication.domain.model.User;

public interface CreateUserInputPort {
    User createUser(User user);
}
