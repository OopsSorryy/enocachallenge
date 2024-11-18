package com.enoca.challenge.order;



import com.enoca.challenge.customer.CustomerConverter;
import com.enoca.challenge.orderproduct.OrderProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderProductConverter orderProductConverter;
    private final CustomerConverter customerConverter;

    public List<OrderResponse> convert(List<Order> fromList){
        return fromList.stream().map(from -> new OrderResponse(
                from.getId(),
                customerConverter.convert(from.getCustomer()),
                orderProductConverter.convert(from.getOrderProducts()),
                from.getTotalPrice()
                )).toList();

    }
}
