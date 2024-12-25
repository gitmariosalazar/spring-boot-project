package com.exa.hexagonal.selling.domain.dto.request;

import com.exa.hexagonal.products.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class SellingItemsRequest {
    private Product product;
    private Integer quantity;
    @JsonIgnore
    private SellingRequest sellingRequest;

    public SellingItemsRequest(){}

    public SellingItemsRequest(Product product, Integer quantity, SellingRequest sellingRequest) {
        this.product = product;
        this.quantity = quantity;
        this.sellingRequest=sellingRequest;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SellingRequest getSellingRequest() {
        return sellingRequest;
    }

    public void setSellingRequest(SellingRequest sellingRequest) {
        this.sellingRequest = sellingRequest;
    }
}
