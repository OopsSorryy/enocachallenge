package com.enoca.challenge.order;

import com.enoca.challenge.customer.CustomerResponse;
import com.enoca.challenge.orderproduct.OrderProductResponse;

import java.util.List;

public record OrderResponse(
        String id,
        CustomerResponse customerResponse,
        List<OrderProductResponse> orderProductResponseList,
        Double totalPrice
) {
}
