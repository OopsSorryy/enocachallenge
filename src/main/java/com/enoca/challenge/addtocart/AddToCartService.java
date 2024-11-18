package com.enoca.challenge.addtocart;


import com.enoca.challenge.cart.Cart;
import com.enoca.challenge.cart.CartService;
import com.enoca.challenge.customer.Customer;
import com.enoca.challenge.customer.CustomerService;
import com.enoca.challenge.constant.Constant;
import com.enoca.challenge.exception.NotFoundException;
import com.enoca.challenge.product.Product;
import com.enoca.challenge.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddToCartService {

    private final AddToCartRepository addToCartRepository;
    private final CustomerService customerService;
    private final CartService cartService;
    private final ProductService productService;
    private final AddToCartConverter addToCartConverter;



    @Transactional
    public void addProductToCart(CreateAddToCartRequest createAddToCartRequest){
        Customer customer = customerService.findById(createAddToCartRequest.customerId());
        Cart cart = cartService.findById(createAddToCartRequest.cartId());
        Product product = productService
                .findProductByProductIdAndSetUnitInStockForAddProductToCart(createAddToCartRequest.productId());


        AddToCart addToCart = new AddToCart(customer,cart,product);

        addToCartRepository.save(addToCart);
    }

    @Transactional
    public void deleteProductFromCart(String addToCartId){
        AddToCart addToCart = findById(addToCartId);
        productService.findProductByProductIdAndSetUnitInStockForDeleteProductFromCart(addToCart.getProduct().getId());
        addToCartRepository.delete(addToCart);
    }

    @Transactional
    public void deleteAllProductFromCart(String cartId){
        Cart cart = cartService.findById(cartId);
        List<AddToCart> addToCartList = addToCartRepository.findAddToCartByCart_Id(cart.getId());
        addToCartList.forEach(addToCart ->
                productService.findProductByProductIdAndSetUnitInStockForDeleteProductFromCart
                        (addToCart.getProduct().getId()));
        addToCartRepository.deleteAll(addToCartList);
    }

    public void deleteAllProductFromCartAfterBuy(String cartId){
        Cart cart = cartService.findById(cartId);
        List<AddToCart> addToCartList = addToCartRepository.findAddToCartByCart_Id(cart.getId());
        addToCartRepository.deleteAll(addToCartList);
    }

    public Double getTotalPriceInCart(String cartId){
        Cart cart = cartService.findById(cartId);
        return addToCartRepository.getTotalPriceByCartId(cart.getId());
    }

    public List<AddToCartResponse> getAllProductInCart(String cartId){
        return addToCartConverter.convert(addToCartRepository.findAddToCartByCart_Id(cartId));
    }

    public List<AddToCart> findAllByCartId(String cartId){
        return addToCartRepository.findAddToCartByCart_Id(cartId);
    }

    protected AddToCart findById(String addToCartId){
        return addToCartRepository.findById(addToCartId)
                .orElseThrow(()-> new NotFoundException(Constant.ADD_TO_CART_NOT_FOUND));
    }
}
