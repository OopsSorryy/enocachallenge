package com.enoca.challenge.addtocart;

import jakarta.validation.constraints.NotBlank;

public record CreateAddToCartRequest(

        @NotBlank
        String customerId,

        @NotBlank
        String cartId,

        @NotBlank
        String productId) {
}
