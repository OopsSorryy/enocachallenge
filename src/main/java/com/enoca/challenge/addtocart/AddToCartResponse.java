package com.enoca.challenge.addtocart;

import com.enoca.challenge.customer.CustomerResponse;
import com.enoca.challenge.product.ProductResponse;

public record AddToCartResponse(String id, CustomerResponse customerResponse, ProductResponse productResponse) {
}
