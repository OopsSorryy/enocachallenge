package com.enoca.challenge.product;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter {

    public List<ProductResponse> getAllConvert(List<Product> fromList) {
        return fromList.stream().map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getUnitInStock()
               )).toList();
    }

    public ProductResponse convert(Product from){
        return new ProductResponse(from.getId(), from.getName(), from.getPrice(), from.getUnitInStock());
    }
}
