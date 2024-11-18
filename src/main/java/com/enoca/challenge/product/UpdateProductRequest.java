package com.enoca.challenge.product;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductRequest(
        @NotBlank
        String id,
        String name,
        Double price,
        Integer unitInStock) {
}
