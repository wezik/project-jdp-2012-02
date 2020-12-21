package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID", unique = true)
    Long id;

    @NotNull
    @Column(name = "NAME")
    String name;

    @NotNull
    @Column(name = "DESCRIPTION")
    String description;

    @NotNull
    @Column(name = "PRICE")
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    Group group;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "productList")
    List<Cart> cartsWhichContainsThisProduct = new ArrayList<>();

    //konstruktor bez ID i listy koszyków dla ułatwienia testów
    public Product(@NotNull @NonNull String name, @NotNull @NonNull String description, @NotNull @NonNull BigDecimal price, @NonNull Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }
}

