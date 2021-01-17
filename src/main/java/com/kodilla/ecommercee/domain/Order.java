package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "ID")
    private Cart cart;

    public Order(@NotNull Double value, @NotNull String status, @NotNull LocalDateTime dateTime, @NotNull String shippingAddress) {
        this.value = value;
        this.status = status;
        this.dateTime = dateTime;
        this.shippingAddress = shippingAddress;
    }
}