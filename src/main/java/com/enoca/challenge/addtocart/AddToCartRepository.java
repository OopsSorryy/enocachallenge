package com.enoca.challenge.addtocart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddToCartRepository extends JpaRepository<AddToCart, String> {

    List<AddToCart> findAddToCartByCart_Id(String cartId);

    @Query("SELECT SUM(p.price) FROM AddToCart a JOIN a.product p WHERE a.cart.id = :cartId")
    Double getTotalPriceByCartId(String cartId);
}
