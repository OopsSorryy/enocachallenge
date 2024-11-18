package com.enoca.challenge.order;


import com.enoca.challenge.addtocart.AddToCart;
import com.enoca.challenge.addtocart.AddToCartService;
import com.enoca.challenge.customer.Customer;
import com.enoca.challenge.customer.CustomerService;
import com.enoca.challenge.orderproduct.CreateOrderProductRequest;
import com.enoca.challenge.orderproduct.OrderProduct;
import com.enoca.challenge.orderproduct.OrderProductService;
import com.enoca.challenge.product.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final AddToCartService addToCartService;
    private final OrderProductService orderProductService;
    private final OrderConverter orderConverter;


    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest){
        Customer customer = customerService.findById(createOrderRequest.customerId());
        List<AddToCart> addToCarts = addToCartService.findAllByCartId(customer.getCart().getId());
        Double totalPriceInCart = addToCartService.getTotalPriceInCart(customer.getCart().getId());

        List<Product> products = addToCarts.stream().map(AddToCart::getProduct).toList();

        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalPrice(totalPriceInCart);

        List<OrderProduct> orderProducts = orderProductService.createOrderProduct(products, order);

        order.setOrderProducts(orderProducts);

        orderRepository.save(order);

        addToCartService.deleteAllProductFromCartAfterBuy(customer.getCart().getId());
    }

    public List<OrderResponse> getAllOrdersForCustomer(String customerId){
        return orderConverter.convert(orderRepository.findOrderByCustomer_Id(customerId));
    }
}
