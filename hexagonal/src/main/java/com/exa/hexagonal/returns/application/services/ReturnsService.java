package com.exa.hexagonal.returns.application.services;

import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.domain.ports.input.*;

import java.util.List;
import java.util.Optional;

public class ReturnsService implements CreateReturnInputPort, AnnulReturnInputPort,
        FindReturnByIdInputPort, FindAllReturnsInputPort,
        DeleteReturnInputPort {

    private final CreateReturnInputPort createReturnInputPort;
    private final AnnulReturnInputPort annulReturnInputPort;
    private final FindReturnByIdInputPort findReturnByIdInputPort;
    private final FindAllReturnsInputPort findAllReturnsInputPort;
    private final DeleteReturnInputPort deleteReturnInputPort;

    public ReturnsService(CreateReturnInputPort createReturnInputPort, AnnulReturnInputPort annulReturnInputPort,
                          FindReturnByIdInputPort findReturnByIdInputPort, FindAllReturnsInputPort findAllReturnsInputPort,
                          DeleteReturnInputPort deleteReturnInputPort){
        this.createReturnInputPort=createReturnInputPort;
        this.annulReturnInputPort=annulReturnInputPort;
        this.findReturnByIdInputPort=findReturnByIdInputPort;
        this.findAllReturnsInputPort=findAllReturnsInputPort;
        this.deleteReturnInputPort=deleteReturnInputPort;
    }

    @Override
    public Returns createReturn(Returns returns) {
        return createReturnInputPort.createReturn(returns);
    }

    @Override
    public Optional<Returns> annulReturn(Long id, String status) {
        return annulReturnInputPort.annulReturn(id, status);
    }

    @Override
    public Optional<Returns> findReturnById(Long id) {
        return findReturnByIdInputPort.findReturnById(id);
    }

    @Override
    public List<Returns> findAllReturns() {
        return findAllReturnsInputPort.findAllReturns();
    }

    @Override
    public boolean deleteReturn(Long id) {
        return deleteReturnInputPort.deleteReturn(id);
    }
}
