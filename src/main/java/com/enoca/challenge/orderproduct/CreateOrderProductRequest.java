package com.enoca.challenge.orderproduct;

import com.enoca.challenge.order.Order;
import com.enoca.challenge.product.Product;

public record CreateOrderProductRequest(Double price, Product product, Order order) {
}
