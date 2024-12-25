package com.exa.hexagonal.selling.infrastructure.repositories.adapters;

import com.exa.hexagonal.authentication.infrastructure.mappers.UserMapper;
import com.exa.hexagonal.products.domain.model.Product;
import com.exa.hexagonal.products.infrastructure.mappers.ProductMapper;
import com.exa.hexagonal.products.infrastructure.model.entities.ProductEntity;
import com.exa.hexagonal.products.infrastructure.repositories.repository.JPARepositoryProduct;
import com.exa.hexagonal.selling.domain.dto.request.SellingRequest;
import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.model.SellingItems;
import com.exa.hexagonal.selling.domain.ports.output.SellingRepositoryOutputPort;
import com.exa.hexagonal.selling.infrastructure.mappers.SellingMapper;
import com.exa.hexagonal.selling.infrastructure.model.entities.SellingEntity;
import com.exa.hexagonal.selling.infrastructure.model.entities.SellingItemsEntity;
import com.exa.hexagonal.selling.infrastructure.repositories.repository.JPARepositorySelling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPARepositorySellingAdapter implements SellingRepositoryOutputPort {
    private final JPARepositorySelling jpaRepositorySelling;
    private final SellingMapper sellingMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    @Autowired
    private JPARepositoryProduct jpaRepositoryProduct;
    public JPARepositorySellingAdapter(JPARepositorySelling jpaRepositorySelling, SellingMapper sellingMapper, UserMapper userMapper, ProductMapper productMapper) {
        this.jpaRepositorySelling = jpaRepositorySelling;
        this.sellingMapper=sellingMapper;
        this.userMapper=userMapper;
        this.productMapper=productMapper;
    }


    @Override
    public Selling createSelling(Selling selling) {
        this.updateProductStock(selling);
        return sellingMapper.fromSellingEntityToSelling(jpaRepositorySelling.save(sellingMapper.fromSellingToSellingEntity(selling)));
    }

    @Override
    public Optional<Selling> updateSelling(Long id, Selling selling) {
        Optional<SellingEntity> sellingFound = jpaRepositorySelling.findById(id);
        if(sellingFound.isPresent()){
            SellingEntity sellingEntity = sellingFound.get();

            sellingEntity.setClient(userMapper.toUserEntity(selling.getClient()));
            sellingEntity.setIva(selling.getIva());
            sellingEntity.setSubtotal(selling.getSubtotal());
            sellingEntity.setTotal(selling.getTotal());

            List<SellingItemsEntity> updatedSellingItems = selling.getSellingItems().stream().map(sellingItems -> {
                SellingItemsEntity sellingItemsEntity = new SellingItemsEntity();
                sellingItemsEntity.setProduct(productMapper.fromProductToProductEntity(sellingItems.getProduct()));
                sellingItemsEntity.setQuantity(sellingItems.getQuantity());
                sellingItemsEntity.setUnitPrice(sellingItems.getUnitPrice());
                sellingItemsEntity.setIva(sellingItems.getIva());
                sellingItemsEntity.setSubtotal(sellingItems.getSubtotal());
                sellingItemsEntity.setTotalPrice(sellingItems.getTotalPrice());
                sellingItemsEntity.setSelling(sellingEntity);
                return sellingItemsEntity;
            }).collect(Collectors.toList());

            sellingEntity.getSellingItems().removeIf(item -> !updatedSellingItems.contains(item));

            sellingEntity.getSellingItems().addAll(updatedSellingItems);

            jpaRepositorySelling.save(sellingEntity);

            return Optional.of(sellingMapper.fromSellingEntityToSelling(sellingEntity));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Selling> findSellingById(Long id) {
        return jpaRepositorySelling.findById(id).map(SellingEntity::toDomainSelling);
    }

    @Override
    public List<Selling> findAllSelling() {
        return jpaRepositorySelling.findAll().stream()
                .map(sellingEntity -> sellingMapper.fromSellingToSellingEntity(sellingMapper.fromSellingEntityToSelling(sellingEntity)))
                .collect(Collectors.toList()).stream().map(SellingEntity::toDomainSelling).collect(Collectors.toList());
    }

    @Override
    public boolean deleteSelling(Long id) {
        if(jpaRepositorySelling.existsById(id)){
            jpaRepositorySelling.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Selling> FindAllSellingByDate(LocalDate sellingDate) {
        return jpaRepositorySelling.findAllBySellingDate(sellingDate).stream()
                .map(sellingEntity -> sellingMapper.fromSellingToSellingEntity(sellingMapper.fromSellingEntityToSelling(sellingEntity)))
                .collect(Collectors.toList()).stream().map(SellingEntity::toDomainSelling).collect(Collectors.toList());
    }

    private void updateProductStock(Selling selling) {
        for (SellingItems item : selling.getSellingItems()) {
            Long productId = item.getProduct().getId();
            int quantitySold = item.getQuantity();
            ProductEntity product = jpaRepositoryProduct.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

            if (product.getQuantity() < quantitySold) {
                throw new IllegalArgumentException("Stock insufficient to the product: " + product.getName());
            }
            product.setQuantity(product.getQuantity() - quantitySold);
            jpaRepositoryProduct.save(product);
        }
    }
}
