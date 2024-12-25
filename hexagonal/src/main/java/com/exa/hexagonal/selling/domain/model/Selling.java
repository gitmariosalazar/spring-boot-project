package com.exa.hexagonal.selling.domain.model;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.products.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Selling {
    private Long id;
    private String sellingCode;
    private LocalDate sellingDate;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    private List<SellingItems> sellingItems;
    private User client;
    private String status;

    public Selling(){}

    public Selling(Long id, String sellingCode, LocalDate sellingDate, BigDecimal subtotal, BigDecimal iva, BigDecimal total, List<SellingItems> sellingItems, User client) {
        this.id = id;
        this.sellingCode = sellingCode;
        this.sellingDate = sellingDate;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.sellingItems = sellingItems;
        this.client = client;
        this.status="delivered";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellingCode() {
        return sellingCode;
    }

    public void setSellingCode(String sellingCode) {
        this.sellingCode = sellingCode;
    }

    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate = sellingDate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<SellingItems> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<SellingItems> sellingItems) {
        this.sellingItems = sellingItems;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
