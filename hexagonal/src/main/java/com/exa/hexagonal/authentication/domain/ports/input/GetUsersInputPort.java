package com.exa.hexagonal.authentication.domain.ports.input;

import com.exa.hexagonal.authentication.domain.model.User;

import java.util.List;

public interface GetUsersInputPort {
    List<User> getUsers();
}
