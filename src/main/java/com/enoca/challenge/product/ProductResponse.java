package com.enoca.challenge.product;

import java.io.Serial;
import java.io.Serializable;

public record ProductResponse(String id, String name, Double price, Integer unitInStock)
        implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

