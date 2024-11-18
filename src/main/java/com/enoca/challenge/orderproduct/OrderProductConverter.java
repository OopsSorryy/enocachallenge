package com.enoca.challenge.orderproduct;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProductConverter {


    public List<OrderProductResponse> convert(List<OrderProduct> fromList){
        return fromList.stream().map(from ->
                new OrderProductResponse(from.getId(), from.getOldPrice(), from.getName(), from.getQuantity())).toList();
    }
}
