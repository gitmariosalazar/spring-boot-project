package com.exa.hexagonal.products.infrastructure.model.entities;

import com.exa.hexagonal.products.domain.model.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    private String description;
    private String mark;
    @Column(name = "supplier_price", nullable = false)
    private BigDecimal supplierPrice;
    private BigDecimal iva;
    @Column(name = "percentage_increment", nullable = false)
    private BigDecimal percentageIncrement;
    @Column(name = "public_price", nullable = false)
    private BigDecimal publicPrice;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    public ProductEntity(){}

    public ProductEntity(Long id, String code, String name, String description, String mark, BigDecimal supplierPrice, BigDecimal iva, BigDecimal percentageIncrement, BigDecimal publicPrice, Integer quantity) {
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

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mark='" + mark + '\'' +
                ", supplierPrice=" + supplierPrice +
                ", iva=" + iva +
                ", percentageIncrement=" + percentageIncrement +
                ", publicPrice=" + publicPrice +
                ", quantity=" + quantity +
                '}';
    }

    public Product toDomainModel(){
        return new Product(id, code, name, description, mark, supplierPrice, iva, percentageIncrement, publicPrice, quantity);
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
