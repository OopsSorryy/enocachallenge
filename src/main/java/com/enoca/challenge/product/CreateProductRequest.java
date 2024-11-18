package com.enoca.challenge.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest(
        @NotBlank
        String name,

        @NotNull
        Double price,

        @NotNull
        Integer unitInStock
) {
}
