package com.enoca.challenge.orderproduct;


import com.enoca.challenge.order.Order;
import com.enoca.challenge.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public List<OrderProduct> createOrderProduct(List<Product> products, Order order ) {
        Map<String, Integer> productQuantities = new HashMap<>();

        products.forEach(product ->
                productQuantities.put(product.getName(),
                        productQuantities.getOrDefault(product.getName(), 0) + 1));


        List<OrderProduct> orderProducts = new ArrayList<>();

        for (Product product : products) {
            boolean exists = orderProducts.stream()
                    .anyMatch(orderProduct -> orderProduct.getName().equals(product.getName()));

            if (!exists) {
                orderProducts.add(new OrderProduct(
                        product.getPrice(),
                        product.getName(),
                        productQuantities.get(product.getName()),
                        order));
            }
        }

        orderProductRepository.saveAll(orderProducts);

        return orderProducts;
    }
}
