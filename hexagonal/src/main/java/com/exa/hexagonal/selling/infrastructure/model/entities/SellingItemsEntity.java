package com.exa.hexagonal.selling.infrastructure.model.entities;

import com.exa.hexagonal.products.infrastructure.model.entities.ProductEntity;
import com.exa.hexagonal.selling.domain.model.SellingItems;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "selling_items")
public class SellingItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_selling_item")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "selling", nullable = false)
    private SellingEntity selling;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    @Column(name = "iva", nullable = false)
    private BigDecimal iva;
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    public SellingItemsEntity(){}

    public SellingItemsEntity(Long id, ProductEntity product, SellingEntity selling, Integer quantity, BigDecimal unitPrice, BigDecimal iva, BigDecimal subtotal, BigDecimal totalPrice) {
        this.id = id;
        this.product = product;
        this.selling = selling;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.iva = iva;
        this.subtotal = subtotal;
        this.totalPrice = totalPrice;
    }

    public SellingItems toDomainSellingItems(){
        return new SellingItems(id, product.toDomainModel(), null, quantity, unitPrice, iva, subtotal, totalPrice);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public SellingEntity getSelling() {
        return selling;
    }

    public void setSelling(SellingEntity selling) {
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
