package com.exa.hexagonal.authentication.domain.ports.output;

import com.exa.hexagonal.authentication.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryOutputPort {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> update(Long id, User user);
    boolean deleteById(Long id);
    Optional<User> findByEmail(String email);
}
