package com.enoca.challenge.order;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findOrderByCustomer_Id(String customerId);
}
