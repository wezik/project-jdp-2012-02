package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    Order order;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinTable(
            name = "CARTS_JOIN_CARTENTRIES",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ENTRY_ID", referencedColumnName = "ID")}
    )
    List<CartEntry> productList = new ArrayList<>();

}
