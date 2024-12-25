package com.exa.hexagonal.products.domain.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String mark;
    private BigDecimal supplierPrice;
    private BigDecimal iva;
    private BigDecimal percentageIncrement;
    private BigDecimal publicPrice;
    private Integer quantity;

    public Product(){}

    public Product(Long id, String code, String name, String description, String mark, BigDecimal supplierPrice, BigDecimal iva, BigDecimal percentageIncrement, BigDecimal publicPrice, Integer quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.mark = mark;
        this.supplierPrice = supplierPrice;
        this.iva = iva;
        this.percentageIncrement = percentageIncrement;
        this.publicPrice = publicPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getPercentageIncrement() {
        return percentageIncrement;
    }

    public void setPercentageIncrement(BigDecimal percentageIncrement) {
        this.percentageIncrement = percentageIncrement;
    }

    public BigDecimal getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(BigDecimal publicPrice) {
        this.publicPrice = publicPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
