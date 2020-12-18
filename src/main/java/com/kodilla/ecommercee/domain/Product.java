package com.kodilla.ecommercee.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID", unique = true)
    Long id;

    @NonNull
    @NotNull
    @Column(name = "NAME")
    String name;

    @NonNull
    @NotNull
    @Column(name = "DESCRIPTION")
    String description;

    @NonNull
    @NotNull
    @Column(name = "PRICE")
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    Group groupId;

    @ManyToMany
    @JoinTable(
            name = "PRODUCTS_IN_CARTS",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    List<Cart> cartsWhichContainsThisProduct = new ArrayList<>();

    public void setGroupId(Group groupId) {
        this.groupId = groupId;
    }
}
