package com.enoca.challenge.orderproduct;


import com.enoca.challenge.baseentity.BaseEntity;
import com.enoca.challenge.order.Order;
import com.enoca.challenge.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct extends BaseEntity {

    private Double oldPrice;

    private String name;

    private Integer quantity;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn
    private Order order;

}
