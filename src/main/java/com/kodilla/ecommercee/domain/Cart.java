package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "Carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CART_ID", unique = true)
    Long id;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "cartsWhichContainsThisProduct")
    List<Product> productList = new ArrayList<>();

}
