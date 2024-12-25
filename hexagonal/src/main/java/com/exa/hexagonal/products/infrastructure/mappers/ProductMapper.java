package com.exa.hexagonal.products.infrastructure.mappers;

import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.dto.response.ProductResponse;
import com.exa.hexagonal.products.infrastructure.model.entities.ProductEntity;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductMapper {

    @Value("${config.percentage}")
    private BigDecimal percentageIncrement;

    public ProductResponse fromProductToProductResponse(Product product){
        return new ProductResponse(
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getMark(),
                product.getSupplierPrice(),
                product.getIva(),
                product.getPercentageIncrement(),
                product.getPublicPrice(),
                product.getQuantity()
        );
    }

    public ProductEntity fromProductToProductEntity(Product product){
        return new ProductEntity(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getMark(),
                product.getSupplierPrice(),
                product.getIva(),
                product.getPercentageIncrement(),
                product.getPublicPrice(),
                product.getQuantity()
        );
    }

    public ProductEntity fromProductRequestToProductEntity(ProductRequest productRequest){
        return new ProductEntity(
                null,
                productRequest.getCode(),
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getMark(),
                productRequest.getSupplierPrice(),
                productRequest.getIva(),
                this.percentageIncrement,
                productRequest.getIva().add(new BigDecimal(100))
                        .divide(new BigDecimal(100),10, RoundingMode.HALF_UP)
                        .multiply(productRequest.getSupplierPrice())
                        .multiply(percentageIncrement.add(new BigDecimal(100))
                                .divide(new BigDecimal(100),10, RoundingMode.HALF_UP)),
                productRequest.getQuantity()
        );
    }

    public ProductRequest fromProductToProductRequest(Product product){
        return new ProductRequest(
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getMark(),
                product.getSupplierPrice(),
                product.getQuantity(),
                product.getIva()
        );
    }

    public Product fromProductEntityToProduct(ProductEntity productEntity){
        return new Product(
                productEntity.getId(),
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getMark(),
                productEntity.getSupplierPrice(),
                productEntity.getIva(),
                productEntity.getPercentageIncrement(),
                productEntity.getPublicPrice(),
                productEntity.getQuantity()
        );
    }

    public Product fromProductRequestToProduct(ProductRequest productRequest){
        return new Product(
                null,
                productRequest.getCode(),
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getMark(),
                productRequest.getSupplierPrice(),
                productRequest.getIva(),
                this.percentageIncrement,
                productRequest.getIva().add(new BigDecimal(100))
                        .divide(new BigDecimal(100),10, RoundingMode.HALF_UP)
                        .multiply(productRequest.getSupplierPrice())
                        .multiply(percentageIncrement.add(new BigDecimal(100))
                                .divide(new BigDecimal(100),10, RoundingMode.HALF_UP)),
                productRequest.getQuantity()
        );
    }
}
