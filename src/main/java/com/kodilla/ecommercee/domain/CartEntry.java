package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "CART_ENTRIES")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setRelationsInCartAndProductJoinTables() {
        cart.getCartEntryList().add(this);
        product.getCartEntriesWhichContainsThisEntry().add(this);
    }

    public void removeRelationsFromCartAndProductTables() {
        cart.getCartEntryList().remove(this);
        product.getCartEntriesWhichContainsThisEntry().remove(this);
    }
}
