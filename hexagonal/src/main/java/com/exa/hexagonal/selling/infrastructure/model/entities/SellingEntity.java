package com.exa.hexagonal.selling.infrastructure.model.entities;

import com.exa.hexagonal.authentication.infrastructure.model.entities.UserEntity;
import com.exa.hexagonal.selling.domain.model.Selling;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "selling")
public class SellingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_selling", nullable = false)
    private Long id;
    @Column(name = "selling_code", unique = true, nullable = false)
    private String sellingCode;
    @Column(name = "selling_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate sellingDate;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    @OneToMany(mappedBy = "selling", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellingItemsEntity> sellingItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity client;
    private String status;

    public SellingEntity() {
    }

    public SellingEntity(Long id, String sellingCode, BigDecimal subtotal, BigDecimal iva, BigDecimal total, List<SellingItemsEntity> sellingItems, UserEntity client) {
        this.id = id;
        this.sellingCode = sellingCode;
        this.sellingDate = LocalDate.now();
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.sellingItems = sellingItems;
        this.client = client;
        this.status = "delivered";
    }

    public Selling toDomainSelling() {
        Selling selling = new Selling();
        selling.setId(id);
        selling.setSellingCode(sellingCode);
        selling.setSellingDate(sellingDate);
        selling.setSubtotal(subtotal);
        selling.setIva(total.subtract(subtotal));
        selling.setTotal(total);
        selling.setSellingItems(
                sellingItems.stream()
                        .map(SellingItemsEntity::toDomainSellingItems)
                        .collect(Collectors.toList())
        );
        selling.setClient(client.toDomainModel());
        selling.setStatus(status);
        return selling;
    }


    @PrePersist
    public void prePersistSellingDate() {
        if (this.sellingDate == null) {
            this.sellingDate = LocalDate.now();
        }
        this.sellingCode = UUID.randomUUID().toString();
        this.status = "delivered";
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

    public List<SellingItemsEntity> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<SellingItemsEntity> sellingItems) {
        this.sellingItems = sellingItems;
    }

    public UserEntity getClient() {
        return client;
    }

    public void setClient(UserEntity client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
