package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "VALUE")
    private Double value;

    @NotNull
    @Column(name = "STATUS")
    private String status ;

    @NotNull
    @Column(name = "DATETIME")
    private LocalDateTime dateTime = LocalDateTime.now();

    @NotNull
    @Column(name = "SHIPPINGADDRESS")
    private String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false , referencedColumnName = "CART_ID")
    private Cart cart;
}