package com.exa.hexagonal.returns.domain.dto.request;

import com.exa.hexagonal.selling.domain.model.Selling;

import java.time.LocalDate;

public class ReturnRequest {
    private Selling selling;
    private String reason;

    public ReturnRequest(){}

    public ReturnRequest(Selling selling, String reason){
        this.selling=selling;
        this.reason=reason;
    }

    public Selling getSelling() {
        return selling;
    }

    public void setSelling(Selling selling) {
        this.selling = selling;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
