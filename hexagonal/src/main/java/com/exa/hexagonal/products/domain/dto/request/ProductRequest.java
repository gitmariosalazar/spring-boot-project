package com.exa.hexagonal.products.domain.dto.request;

import java.math.BigDecimal;

public class ProductRequest {
    private String code;
    private String name;
    private String description;
    private String mark;
    private BigDecimal supplierPrice;
    private Integer quantity;
    private BigDecimal iva;

    public ProductRequest(){}

    public ProductRequest(String code, String name, String description, String mark, BigDecimal supplierPrice, Integer quantity, BigDecimal iva) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.mark = mark;
        this.supplierPrice = supplierPrice;
        this.quantity = quantity;
        this.iva = iva;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }
}
