package com.exa.hexagonal.products.infrastructure.repositories.adapters;

import com.exa.hexagonal.products.domain.dto.request.ProductRequest;
import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.domain.ports.output.ProductRepositoryOutputPort;
import com.exa.hexagonal.products.infrastructure.mappers.ProductMapper;
import com.exa.hexagonal.products.infrastructure.model.entities.ProductEntity;
import com.exa.hexagonal.products.infrastructure.repositories.repository.JPARepositoryProduct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPARepositoryProductAdapter implements ProductRepositoryOutputPort {

    private final ProductMapper productMapper;
    private final JPARepositoryProduct jpaRepositoryProduct;

    public JPARepositoryProductAdapter(ProductMapper productMapper, JPARepositoryProduct jpaRepositoryProduct){
        this.productMapper=productMapper;
        this.jpaRepositoryProduct=jpaRepositoryProduct;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        return productMapper.fromProductEntityToProduct(jpaRepositoryProduct.save(productMapper.fromProductRequestToProductEntity(productRequest)));
    }

    @Override
    public Optional<Product> updateProduct(String code,ProductRequest productRequest) {
        Optional<ProductEntity> productFound = jpaRepositoryProduct.findByCode(code);
        System.out.println(productFound.toString());
        if (productFound.isPresent()){
            ProductEntity productEntity = productFound.get();
            productEntity.setCode(productRequest.getCode());
            productEntity.setName(productRequest.getName());
            productEntity.setDescription(productRequest.getDescription());
            productEntity.setMark(productRequest.getMark());
            productEntity.setIva(productRequest.getIva());
            productEntity.setPublicPrice(productRequest.getIva().add(new BigDecimal(100)).divide(new BigDecimal(100)).multiply(productRequest.getSupplierPrice()).multiply(productFound.get().getPercentageIncrement().add(new BigDecimal(100)).divide(new BigDecimal(100))));
            productEntity.setSupplierPrice(productRequest.getSupplierPrice());
            productEntity.setQuantity(productRequest.getQuantity());

            ProductEntity productEntityUpdated = jpaRepositoryProduct.save(productEntity);
            return  Optional.of(productMapper.fromProductEntityToProduct(productEntityUpdated));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return jpaRepositoryProduct.findById(id).map(ProductEntity::toDomainModel);
    }

    @Override
    public Optional<Product> findProductByCode(String code) {
        return jpaRepositoryProduct.findByCode(code).map(ProductEntity::toDomainModel);
    }

    @Override
    public boolean deleteProduct(Long id) {
        if(jpaRepositoryProduct.existsById(id)){
            jpaRepositoryProduct.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findAllProducts() {
        return jpaRepositoryProduct.findAll().stream()
                .map(productMapper::fromProductEntityToProduct).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllProductWarningStock(Integer quantityLimit) {
        return jpaRepositoryProduct.findByQuantityLessThan(quantityLimit).stream()
                .map(productMapper::fromProductEntityToProduct).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllProductsUnpurchased() {
        return jpaRepositoryProduct.findUnpurchasedProducts().stream().map(productMapper::fromProductEntityToProduct).collect(Collectors.toList());
    }

}
