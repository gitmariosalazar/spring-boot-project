package com.exa.hexagonal.products.infrastructure.repositories.repository;

import com.exa.hexagonal.products.infrastructure.model.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JPARepositoryProduct extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByCode(String code);
    List<ProductEntity> findByQuantityLessThan(Integer quantityLimit);

    @Query("SELECT p FROM ProductEntity p LEFT JOIN SellingItemsEntity si ON p.id = si.product.id WHERE si.id is NULL")
    List<ProductEntity> findUnpurchasedProducts();
}
