package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "CART_PRODUCT")
public class CartProduct {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    Long id;

    @NotNull
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CART_ID")
    Cart cart;

    @NotNull
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PRODUCT_ID")
    Product product;

    @NotNull
    @NonNull
    @Column(name = "QUANTITY")
    Long quantity;
}
