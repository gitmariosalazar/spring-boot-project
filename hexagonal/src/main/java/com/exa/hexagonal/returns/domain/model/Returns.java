package com.exa.hexagonal.returns.domain.model;

import com.exa.hexagonal.selling.domain.model.Selling;

import java.time.LocalDate;

public class Returns {
    private Long id;
    private Selling selling;
    private LocalDate returnDate;
    private String reason;
    private String status;

    public Returns(){}

    public Returns(Long id, Selling selling, String reason) {
        this.id = id;
        this.selling = selling;
        this.returnDate = LocalDate.now();
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Selling getSelling() {
        return selling;
    }

    public void setSelling(Selling selling) {
        this.selling = selling;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
