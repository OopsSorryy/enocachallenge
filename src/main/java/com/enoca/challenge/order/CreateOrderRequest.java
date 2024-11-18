package com.enoca.challenge.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(

        @NotBlank
        String customerId
) {
}
