package com.exa.hexagonal.selling.domain.dto.request;

import com.exa.hexagonal.authentication.domain.model.User;
import com.exa.hexagonal.selling.domain.model.SellingItems;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SellingRequest {
    private List<SellingItemsRequest> sellingItemsRequests;
    private User client;

    public SellingRequest(){}

    public SellingRequest( List<SellingItemsRequest> sellingItemsRequests, User client) {
        this.sellingItemsRequests = sellingItemsRequests;
        this.client = client;
    }

    public List<SellingItemsRequest> getSellingItemsRequests() {
        return sellingItemsRequests;
    }

    public void setSellingItemsRequests(List<SellingItemsRequest> sellingItemsRequests) {
        this.sellingItemsRequests = sellingItemsRequests;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
