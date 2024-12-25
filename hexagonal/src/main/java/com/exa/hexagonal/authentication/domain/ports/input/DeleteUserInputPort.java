package com.exa.hexagonal.authentication.domain.ports.input;

public interface DeleteUserInputPort {
    boolean deleteUser(Long id);
}
