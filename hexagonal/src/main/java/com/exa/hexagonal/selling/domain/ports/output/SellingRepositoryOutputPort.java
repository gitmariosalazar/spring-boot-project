package com.exa.hexagonal.selling.domain.ports.output;

import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.selling.domain.model.Selling;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SellingRepositoryOutputPort {
    Selling createSelling(Selling selling);
    Optional<Selling> updateSelling(Long id, Selling selling);
    Optional<Selling> findSellingById(Long id);
    List<Selling> findAllSelling();
    boolean deleteSelling(Long id);
    List<Selling> FindAllSellingByDate(LocalDate sellingDate);
}
