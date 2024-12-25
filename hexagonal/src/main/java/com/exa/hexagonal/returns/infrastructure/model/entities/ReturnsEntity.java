package com.exa.hexagonal.returns.infrastructure.model.entities;

import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.selling.infrastructure.model.entities.SellingEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "returns")
public class ReturnsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "selling", referencedColumnName = "id_selling")
    private SellingEntity selling;
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;
    @Column(name = "reason", nullable = false)
    private String reason;
    private String status;

    public ReturnsEntity(){}

    public ReturnsEntity(Long id, SellingEntity selling, String reason) {
        this.id = id;
        this.selling = selling;
        this.returnDate = LocalDate.now();
        this.reason = reason;
    }

    public Returns toDomainModel(){
        Returns returns = new Returns(id, selling.toDomainSelling(), reason);
        returns.setStatus(this.getStatus());
        return returns;
    }

    @PrePersist
    public void prePersistReturn() {
        if (this.status == null) {
            this.status = "returned";
        }
        if (this.returnDate == null) {
            this.returnDate = LocalDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SellingEntity getSelling() {
        return selling;
    }

    public void setSelling(SellingEntity selling) {
        this.selling = selling;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
