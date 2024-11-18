package com.enoca.challenge.product;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){
        productService.createProduct(createProductRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid UpdateProductRequest updateProductRequest){
        productService.updateProduct(updateProductRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable @NotBlank String id ){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @NotBlank String id ){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
