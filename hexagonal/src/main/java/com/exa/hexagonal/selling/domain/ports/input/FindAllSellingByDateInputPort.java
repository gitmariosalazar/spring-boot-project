package com.exa.hexagonal.selling.domain.ports.input;

import com.exa.hexagonal.selling.domain.model.Selling;

import java.time.LocalDate;
import java.util.List;

public interface FindAllSellingByDateInputPort {
    List<Selling> FindAllSellingByDate(LocalDate sellingDate);
}
