package com.enoca.challenge.order;

import com.enoca.challenge.baseentity.BaseEntity;
import com.enoca.challenge.customer.Customer;
import com.enoca.challenge.orderproduct.OrderProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    private Double totalPrice;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn
    private Customer customer;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY,mappedBy = "order")
    private List<OrderProduct> orderProducts;
}