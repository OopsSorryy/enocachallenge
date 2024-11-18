package com.enoca.challenge.order;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest){
        orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForCustomer(@PathVariable @NotBlank String customerId){
        return new ResponseEntity<>(orderService.getAllOrdersForCustomer(customerId),HttpStatus.OK);
    }
}
