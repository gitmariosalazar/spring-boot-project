package com.exa.hexagonal.returns.infrastructure.mappers;

import com.exa.hexagonal.returns.domain.dto.request.ReturnRequest;
import com.exa.hexagonal.returns.domain.model.Returns;
import com.exa.hexagonal.returns.infrastructure.model.entities.ReturnsEntity;
import com.exa.hexagonal.selling.infrastructure.mappers.SellingMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ReturnsMapper {

    @Autowired
    private SellingMapper sellingMapper;

    public Returns fromReturnRequestToReturns(ReturnRequest returnRequest){
        Returns returns = new Returns();
        returns.setSelling(returnRequest.getSelling());
        returns.setReason(returnRequest.getReason());
        return returns;
    }

    public ReturnsEntity fromReturnRequestToReturnsEntity(ReturnRequest returnRequest){
        ReturnsEntity returnsEntity=new ReturnsEntity();
        returnsEntity.setSelling(sellingMapper.fromSellingToSellingEntity(returnRequest.getSelling()));
        returnsEntity.setReason(returnRequest.getReason());
        return returnsEntity;
    }

    public ReturnsEntity fromReturnsToReturnEntity(Returns returns){
        ReturnsEntity returnsEntity = new ReturnsEntity();
        returnsEntity.setSelling(sellingMapper.fromSellingToSellingEntity(returns.getSelling()));
        returnsEntity.setReason(returns.getReason());
        return returnsEntity;
    }

    public Returns fromRetursEntityToReturns(ReturnsEntity returnsEntity){
        Returns returns = new Returns();
        returns.setId(returnsEntity.getId());
        returns.setSelling(returnsEntity.getSelling().toDomainSelling());
        returns.setReason(returnsEntity.getReason());
        returns.setReturnDate(returnsEntity.getReturnDate());
        returns.setStatus(returnsEntity.getStatus());
        return returns;
    }
}
