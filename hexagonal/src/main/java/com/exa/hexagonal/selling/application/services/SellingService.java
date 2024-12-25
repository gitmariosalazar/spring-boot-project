package com.exa.hexagonal.selling.application.services;

import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.model.SellingItems;
import com.exa.hexagonal.selling.domain.ports.input.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SellingService implements CreateSellingInputPort, FindSellingByIdInputPort,
        UpdateSellingInputPort, FindAllSellingInputPort,
        DeleteSellingInputPort, FindAllSellingByDateInputPort
         {

    private final CreateSellingInputPort createSellingInputPort;
    private final FindSellingByIdInputPort findSellingByIdInputPort;
    private final UpdateSellingInputPort updateSellingInputPort;
    private final FindAllSellingInputPort findAllSellingInputPort;
    private final DeleteSellingInputPort deleteSellingInputPort;
    private final FindAllSellingByDateInputPort findAllSellingByDateInputPort;

    public SellingService(CreateSellingInputPort createSellingInputPort, FindSellingByIdInputPort findSellingByIdInputPort,
                          UpdateSellingInputPort updateSellingInputPort, FindAllSellingInputPort findAllSellingInputPort,
                          DeleteSellingInputPort deleteSellingInputPort, FindAllSellingByDateInputPort findAllSellingByDateInputPort
    ){
        this.createSellingInputPort=createSellingInputPort;
        this.findSellingByIdInputPort=findSellingByIdInputPort;
        this.updateSellingInputPort=updateSellingInputPort;
        this.findAllSellingInputPort=findAllSellingInputPort;
        this.deleteSellingInputPort=deleteSellingInputPort;
        this.findAllSellingByDateInputPort=findAllSellingByDateInputPort;
    }

    @Override
    public Selling createSelling(Selling selling) {
        this.calculateSellingTotals(selling);
        return createSellingInputPort.createSelling(selling);
    }

    private void calculateSellingTotals(Selling selling) {
        BigDecimal subtotal = selling.getSellingItems().stream()
                .map(SellingItems::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal iva = selling.getSellingItems().stream()
                .map(SellingItems::getIva)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal total = subtotal.add(iva);

        selling.setSubtotal(subtotal);
        selling.setIva(iva);
        selling.setTotal(total);
    }

    @Override
    public Optional<Selling> findSellingById(Long id) {
        return findSellingByIdInputPort.findSellingById(id);
    }

    @Override
    public Optional<Selling> updateSelling(Long id, Selling selling) {
        this.calculateSellingTotals(selling);
        return updateSellingInputPort.updateSelling(id, selling);
    }

    @Override
    public List<Selling> findAllSelling() {
        return findAllSellingInputPort.findAllSelling();
    }

    @Override
    public boolean deleteSelling(Long id) {
        return deleteSellingInputPort.deleteSelling(id);
    }

    @Override
    public List<Selling> FindAllSellingByDate(LocalDate sellingDate) {
        return findAllSellingByDateInputPort.FindAllSellingByDate(sellingDate);
    }

}
