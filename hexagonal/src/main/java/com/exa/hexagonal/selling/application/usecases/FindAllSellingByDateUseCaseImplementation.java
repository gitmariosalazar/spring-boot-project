package com.exa.hexagonal.selling.application.usecases;

import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.ports.input.FindAllSellingByDateInputPort;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;

import java.time.LocalDate;
import java.util.List;

public class FindAllSellingByDateUseCaseImplementation implements FindAllSellingByDateInputPort {

    private final SellingRepositoryOutputPort sellingRepositoryOutputPort;

    public FindAllSellingByDateUseCaseImplementation(SellingRepositoryOutputPort sellingRepositoryOutputPort){
        this.sellingRepositoryOutputPort=sellingRepositoryOutputPort;
    }

    @Override
    public List<Selling> FindAllSellingByDate(LocalDate sellingDate) {
        return sellingRepositoryOutputPort.FindAllSellingByDate(sellingDate);
    }
}
