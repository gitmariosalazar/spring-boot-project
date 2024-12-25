package com.exa.hexagonal.selling.domain.ports.input;

import com.exa.hexagonal.selling.domain.dto.request.SellingRequest;
import com.exa.hexagonal.selling.domain.model.Selling;

public interface CreateSellingInputPort {
    Selling createSelling(Selling selling);
}
