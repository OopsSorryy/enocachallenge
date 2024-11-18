package com.enoca.challenge.addtocart;


import com.enoca.challenge.customer.CustomerConverter;
import com.enoca.challenge.product.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddToCartConverter {

    private final CustomerConverter customerConverter;
    private final ProductConverter productConverter;

    public List<AddToCartResponse> convert(List<AddToCart> fromList) {
        return fromList.stream().map(from -> new AddToCartResponse
                (
                        from.getId(),
                        customerConverter.convert(from.getCustomer()),
                        productConverter.convert(from.getProduct())
                )).toList();
    }
}
