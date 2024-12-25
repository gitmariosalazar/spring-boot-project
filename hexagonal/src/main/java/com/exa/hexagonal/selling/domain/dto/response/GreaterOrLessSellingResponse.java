package com.exa.hexagonal.selling.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GreaterOrLessSellingResponse {
    private Long sellingId;
    private String sellingCode;
    private BigDecimal total;
    private Integer quantityProducts;
    private String firstname;
    private String lastname;
    private LocalDate sellingDate;

    public GreaterOrLessSellingResponse(){}

    public GreaterOrLessSellingResponse(Long sellingId, String sellingCode, BigDecimal total, Integer quantityProducts, String firstname, String lastname, LocalDate sellingDate) {
        this.sellingId = sellingId;
        this.sellingCode = sellingCode;
        this.total = total;
        this.quantityProducts = quantityProducts;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sellingDate = sellingDate;
    }

    public Long getSellingId() {
        return sellingId;
    }

    public void setSellingId(Long sellingId) {
        this.sellingId = sellingId;
    }

    public String getSellingCode() {
        return sellingCode;
    }

    public void setSellingCode(String sellingCode) {
        this.sellingCode = sellingCode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getQuantityProducts() {
        return quantityProducts;
    }

    public void setQuantityProducts(Integer quantityProducts) {
        this.quantityProducts = quantityProducts;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate = sellingDate;
    }
}
