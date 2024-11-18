package com.enoca.challenge.addtocart;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addToCarts")
@RequiredArgsConstructor
@Validated
public class AddToCartController {

    private final AddToCartService addToCartService;

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestBody @Valid CreateAddToCartRequest createAddToCartRequest){
        addToCartService.addProductToCart(createAddToCartRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllProductInCart/{cartId}")
    public ResponseEntity<List<AddToCartResponse>> getAllProductInCart(@PathVariable @NotBlank String cartId){
        return new ResponseEntity<>(addToCartService.getAllProductInCart(cartId),HttpStatus.OK);
    }
    @GetMapping("/getTotalPriceInCart/{cartId}")
    public ResponseEntity<Double> getTotalPriceInCart(@PathVariable @NotBlank String cartId){
        return new ResponseEntity<>(addToCartService.getTotalPriceInCart(cartId),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProductFromCart/{addToCartId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable @NotBlank String addToCartId){
        addToCartService.deleteProductFromCart(addToCartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllProductFromCart/{cartId}")
    public ResponseEntity<Void> deleteAllProductFromCart(@PathVariable @NotBlank String cartId){
        addToCartService.deleteAllProductFromCart(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
