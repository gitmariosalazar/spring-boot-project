package com.exa.hexagonal.selling.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TotalSellingResponse {
    private LocalDate date;
    private BigDecimal totalAmount;
    private Integer quantitySelling;

    public TotalSellingResponse(){}

    public TotalSellingResponse(LocalDate date, BigDecimal totalAmount, Integer quantitySelling) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.quantitySelling = quantitySelling;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getQuantitySelling() {
        return quantitySelling;
    }

    public void setQuantitySelling(Integer quantitySelling) {
        this.quantitySelling = quantitySelling;
    }
}
