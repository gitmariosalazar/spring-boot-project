package com.exa.hexagonal.returns.infrastructure.repositories.adapters;

import com.exa.hexagonal.products.infrastructure.model.entities.ProductEntity;
import com.exa.hexagonal.products.infrastructure.repositories.repository.JPARepositoryProduct;
import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.domain.ports.output.ReturnsRepositoryOutputPort;
import com.exa.hexagonal.returns.infrastructure.mappers.ReturnsMapper;
import com.exa.hexagonal.returns.infrastructure.model.entities.ReturnsEntity;
import com.exa.hexagonal.returns.infrastructure.repositories.repository.JPARepositoryReturn;
import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.model.SellingItems;
import com.exa.hexagonal.selling.infrastructure.model.entities.SellingEntity;
import com.exa.hexagonal.selling.infrastructure.repositories.repository.JPARepositorySelling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JPARepositoryReturnsAdapter implements ReturnsRepositoryOutputPort {

    private final JPARepositoryReturn jpaRepositoryReturn;
    private final ReturnsMapper returnsMapper;
    @Autowired
    private JPARepositoryProduct jpaRepositoryProduct;
    @Autowired
    private JPARepositorySelling jpaRepositorySelling;
    private final String status = "annulled";

    public JPARepositoryReturnsAdapter(JPARepositoryReturn jpaRepositoryReturn, ReturnsMapper returnsMapper) {
        this.jpaRepositoryReturn = jpaRepositoryReturn;
        this.returnsMapper = returnsMapper;
    }

    @Override
    public Returns createReturn(Returns returns) {
        Returns returnsCreated = returnsMapper.fromRetursEntityToReturns(jpaRepositoryReturn.save(returnsMapper.fromReturnsToReturnEntity(returns)));
        if (returnsCreated.getId() != null) {
            this.updateAtCreated(returnsCreated.getSelling());
        }
        returnsCreated.getSelling().setStatus(this.status);
        return returnsCreated;
    }

    @Override
    public Optional<Returns> annulReturn(Long id, String status) {
        Optional<ReturnsEntity> returnsEntity = jpaRepositoryReturn.findById(id);
        if (returnsEntity.isPresent()) {
            ReturnsEntity returns = returnsEntity.get();
            this.updateAtAnnulOrDelete(returns.toDomainModel().getSelling(), returns.getStatus());
            returns.setStatus(status);
            jpaRepositoryReturn.save(returns);
            return Optional.of(returns.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Returns> findReturnById(Long id) {
        return jpaRepositoryReturn.findById(id).map(ReturnsEntity::toDomainModel);
    }

    @Override
    public List<Returns> findAllReturns() {
        return jpaRepositoryReturn.findAll().stream()
                .map(returnsMapper::fromRetursEntityToReturns)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteReturn(Long id) {
        Optional<ReturnsEntity> returnsEntity = jpaRepositoryReturn.findById(id);
        if (returnsEntity.isPresent()) {
            ReturnsEntity returns = returnsEntity.get();
            this.updateAtAnnulOrDelete(returns.toDomainModel().getSelling(), returns.getStatus());
            jpaRepositoryReturn.deleteById(id);
            return true;
        }
        return false;
    }

    private void updateAtCreated(Selling selling) {
        for (SellingItems item : selling.getSellingItems()) {
            Long productId = item.getProduct().getId();
            int quantitySold = item.getQuantity();
            ProductEntity product = jpaRepositoryProduct.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

            product.setQuantity(product.getQuantity() + quantitySold);
            jpaRepositoryProduct.save(product);
        }
        SellingEntity sellingEntityFound = jpaRepositorySelling.getReferenceById(selling.getId());
        sellingEntityFound.setStatus(this.status);
        jpaRepositorySelling.save(sellingEntityFound);
    }

    private void updateAtAnnulOrDelete(Selling selling, String status) {
        for (SellingItems item : selling.getSellingItems()) {
            Long productId = item.getProduct().getId();
            int quantitySold = item.getQuantity();
            ProductEntity product = jpaRepositoryProduct.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

            if (status.equals(this.status)) {
                product.setQuantity(product.getQuantity());
            } else {
                product.setQuantity(product.getQuantity() - quantitySold);
            }

            jpaRepositoryProduct.save(product);
        }
        SellingEntity sellingEntityFound = jpaRepositorySelling.getReferenceById(selling.getId());
        sellingEntityFound.setStatus("delivered");
        jpaRepositorySelling.save(sellingEntityFound);
    }
}
