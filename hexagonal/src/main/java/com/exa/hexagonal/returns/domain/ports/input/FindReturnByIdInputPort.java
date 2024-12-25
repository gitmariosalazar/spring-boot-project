package com.exa.hexagonal.returns.domain.ports.input;

import com.exa.hexagonal.returns.domain.model.Returns;

import java.util.Optional;

public interface FindReturnByIdInputPort {
    Optional<Returns> findReturnById(Long id);
}
