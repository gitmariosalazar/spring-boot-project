package com.exa.hexagonal.selling.domain.model;

import com.exa.hexagonal.products.domain.model.Product;

import java.math.BigDecimal;

public class SellingItems {
    private Long id;
    private Product product;
    private Selling selling;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal iva;
    private BigDecimal subtotal;
    private BigDecimal totalPrice;

    public SellingItems(){}

    public SellingItems(Long id, Product product, Selling selling, Integer quantity, BigDecimal unitPrice, BigDecimal iva, BigDecimal subtotal, BigDecimal totalPrice) {
        this.id = id;
        this.product = product;
        this.selling = selling;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.iva = iva;
        this.subtotal = subtotal;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Selling getSelling() {
        return selling;
    }

    public void setSelling(Selling selling) {
        this.selling = selling;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
