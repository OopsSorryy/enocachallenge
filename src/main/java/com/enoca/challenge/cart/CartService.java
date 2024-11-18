package com.enoca.challenge.cart;


import com.enoca.challenge.constant.Constant;
import com.enoca.challenge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart createCart(){
        return new Cart();
    }

    public Cart findById(String id){
        return cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.CART_NOT_FOUND));
    }
}
