package com.enoca.challenge.product;

import com.enoca.challenge.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product extends BaseEntity implements Serializable {

    private String name;

    private Double price;

    private Integer unitInStock;

}
