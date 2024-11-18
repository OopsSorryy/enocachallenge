package com.enoca.challenge.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CustomerResponse convert(Customer from){
        return new CustomerResponse(from.getId(), from.getFirstName(), from.getLastName());
    }

}
