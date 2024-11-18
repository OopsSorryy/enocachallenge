package com.enoca.challenge.customer;


import com.enoca.challenge.baseentity.BaseEntity;
import com.enoca.challenge.cart.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends BaseEntity {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn
    private Cart cart;

}
