package com.exa.hexagonal.returns.domain.ports.output;

import com.exa.hexagonal.returns.domain.model.Returns;

import java.util.List;
import java.util.Optional;

public interface ReturnsRepositoryOutputPort {
    Returns createReturn(Returns returns);
    Optional<Returns> annulReturn(Long id, String status);
    Optional<Returns> findReturnById(Long id);
    List<Returns> findAllReturns();
    boolean deleteReturn(Long id);
}
