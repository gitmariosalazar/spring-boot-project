package com.exa.hexagonal.returns.domain.ports.input;

import com.exa.hexagonal.returns.domain.model.Returns;

import java.util.List;

public interface FindAllReturnsInputPort {
    List<Returns> findAllReturns();
}
