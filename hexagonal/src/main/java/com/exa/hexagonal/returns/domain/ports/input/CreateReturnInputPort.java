package com.exa.hexagonal.returns.domain.ports.input;

import com.exa.hexagonal.returns.domain.model.Returns;

public interface CreateReturnInputPort {
    Returns createReturn(Returns returns);
}
