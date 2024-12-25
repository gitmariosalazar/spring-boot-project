package com.exa.hexagonal.selling.domain.ports.input;

import com.exa.hexagonal.selling.domain.model.Selling;

import java.util.List;

public interface FindAllSellingInputPort {
    List<Selling> findAllSelling();
}
