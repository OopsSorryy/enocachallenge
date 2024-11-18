package com.enoca.challenge.cart;

import com.enoca.challenge.baseentity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
public class Cart extends BaseEntity {


}
