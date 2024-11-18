package com.enoca.challenge.customer;


import com.enoca.challenge.cart.CartService;
import com.enoca.challenge.exception.AlreadyExistException;
import com.enoca.challenge.constant.Constant;
import com.enoca.challenge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CartService cartService;

    public void createCustomer(CreateCustomerRequest createCustomerRequest) {
        if (customerRepository.findCustomerByEmail(createCustomerRequest.email()).isPresent()) {
            throw new AlreadyExistException(Constant.CUSTOMER_ALREADY_EXIST);
        }

        Customer customer = new Customer
                (
                        createCustomerRequest.email(),
                        createCustomerRequest.password(),
                        createCustomerRequest.firstName(),
                        createCustomerRequest.lastName(),
                        cartService.createCart()
                );

        customerRepository.save(customer);
    }

    public Customer findById(String id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.CUSTOMER_NOT_FOUND));
    }

}
