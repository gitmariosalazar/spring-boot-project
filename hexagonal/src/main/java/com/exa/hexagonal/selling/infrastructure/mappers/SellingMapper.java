package com.exa.hexagonal.selling.infrastructure.mappers;

import com.exa.hexagonal.authentication.infrastructure.mappers.UserMapper;
import com.exa.hexagonal.products.infrastructure.mappers.ProductMapper;
import com.exa.hexagonal.selling.domain.dto.request.SellingItemsRequest;
import com.exa.hexagonal.selling.domain.dto.request.SellingRequest;
import com.exa.hexagonal.selling.domain.model.Selling;
import com.exa.hexagonal.selling.domain.model.SellingItems;
import com.exa.hexagonal.selling.infrastructure.model.entities.SellingEntity;
import com.exa.hexagonal.selling.infrastructure.model.entities.SellingItemsEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

public class SellingMapper {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;


    public Selling fromSellingEntityToSelling(SellingEntity sellingEntity){
        Selling selling = new Selling();
        selling.setId(sellingEntity.getId());
        selling.setSellingCode(sellingEntity.getSellingCode());
        selling.setSellingDate(sellingEntity.getSellingDate());
        selling.setSubtotal(sellingEntity.getSubtotal());
        selling.setIva(sellingEntity.getTotal().subtract(sellingEntity.getSubtotal()));
        selling.setTotal(sellingEntity.getTotal());
        selling.setClient(sellingEntity.getClient().toDomainModel());
        selling.setStatus(sellingEntity.getStatus());
        if (sellingEntity.getSellingItems() != null) {
            selling.setSellingItems(
                    sellingEntity.getSellingItems().stream()
                            .map(this::fromSellingItemsEntityToSellingItems)
                            .collect(Collectors.toList())
            );
        }
        return selling;
    }

    public SellingItems fromSellingItemsEntityToSellingItems(SellingItemsEntity sellingItemsEntity)  {
        SellingItems item = new SellingItems();
        BigDecimal subTotal = sellingItemsEntity.getProduct().getPublicPrice().multiply(new BigDecimal(sellingItemsEntity.getQuantity())).divide(
                sellingItemsEntity.getProduct().getIva().add(new BigDecimal(100)).divide(new BigDecimal(100), 10, RoundingMode.HALF_UP),
                2, RoundingMode.HALF_UP
        );
        BigDecimal total = sellingItemsEntity.getProduct().getPublicPrice().multiply(new BigDecimal(sellingItemsEntity.getQuantity()));
        item.setId(sellingItemsEntity.getId());
        item.setProduct(productMapper.fromProductEntityToProduct(sellingItemsEntity.getProduct()));
        item.setQuantity(sellingItemsEntity.getQuantity());
        item.setUnitPrice(sellingItemsEntity.getProduct().getPublicPrice());
        item.setIva(total.subtract(subTotal));
        item.setTotalPrice(sellingItemsEntity.getTotalPrice());
        item.setSubtotal(subTotal);
        item.setTotalPrice(total);
        return item;
    }

    public SellingEntity fromSellingToSellingEntity(Selling selling) {
        SellingEntity sellingEntity = new SellingEntity();
        sellingEntity.setId(selling.getId());
        sellingEntity.setSellingCode(selling.getSellingCode());
        sellingEntity.setSellingDate(selling.getSellingDate());
        sellingEntity.setClient(userMapper.toUserEntity(selling.getClient()));
        sellingEntity.setSubtotal(selling.getSubtotal());
        sellingEntity.setIva(selling.getTotal().subtract(selling.getSubtotal()));
        sellingEntity.setTotal(selling.getTotal());
        sellingEntity.setClient(userMapper.toUserEntity(selling.getClient()));
        sellingEntity.setStatus(selling.getStatus());
        if (selling.getSellingItems() != null) {
            sellingEntity.setSellingItems(
                    selling.getSellingItems().stream()
                            .map(sellingItem -> {
                                SellingItemsEntity sellingItemsEntity = fromSellingItemsToSellingItemsEntity(sellingItem);
                                sellingItemsEntity.setSelling(sellingEntity);
                                return sellingItemsEntity;
                            })
                            .collect(Collectors.toList())
            );
        }

        return sellingEntity;
    }

    public Selling fromSellingRequestToSelling(SellingRequest sellingRequest) {
        Selling selling = new Selling();
        selling.setClient(sellingRequest.getClient());

        if (sellingRequest.getSellingItemsRequests() != null) {
            selling.setSellingItems(
                    sellingRequest.getSellingItemsRequests().stream()
                            .map(SellingMapper::fromSellingItemsRequestToSellingItems)
                            .collect(Collectors.toList())
            );
        }
        return selling;
    }

    public SellingItemsEntity fromSellingItemsToSellingItemsEntity(SellingItems sellingItems)  {
        SellingItemsEntity item = new SellingItemsEntity();
        BigDecimal subTotal = sellingItems.getProduct().getPublicPrice().multiply(new BigDecimal(sellingItems.getQuantity())).divide(
                sellingItems.getProduct().getIva().add(new BigDecimal(100)).divide(new BigDecimal(100), 10, RoundingMode.HALF_UP),
                2, RoundingMode.HALF_UP);
        BigDecimal total = sellingItems.getProduct().getPublicPrice().multiply(new BigDecimal(sellingItems.getQuantity()));
        item.setProduct(productMapper.fromProductToProductEntity(sellingItems.getProduct()));
        item.setQuantity(sellingItems.getQuantity());
        item.setUnitPrice(sellingItems.getProduct().getPublicPrice());
        item.setIva(total.subtract(subTotal));
        item.setId(sellingItems.getId());
        item.setSubtotal(subTotal);
        item.setTotalPrice(total);
        return item;
    }

    private static SellingItems fromSellingItemsRequestToSellingItems(SellingItemsRequest sellingItemsRequest) {
        SellingItems item = new SellingItems();
        BigDecimal subTotal = sellingItemsRequest.getProduct().getPublicPrice()
                .multiply(new BigDecimal(sellingItemsRequest.getQuantity()))
                .divide(sellingItemsRequest.getProduct().getIva()
                                .add(new BigDecimal(100))
                                .divide(new BigDecimal(100), 10, RoundingMode.HALF_UP),
                        2, RoundingMode.HALF_UP
                );

        BigDecimal total = sellingItemsRequest.getProduct().getPublicPrice().multiply(new BigDecimal(sellingItemsRequest.getQuantity()));
        item.setProduct(sellingItemsRequest.getProduct());
        item.setQuantity(sellingItemsRequest.getQuantity());
        item.setUnitPrice(sellingItemsRequest.getProduct().getPublicPrice());
        item.setIva(total.subtract(subTotal));
        item.setSubtotal(subTotal);
        item.setTotalPrice(total);
        return item;
    }

}
