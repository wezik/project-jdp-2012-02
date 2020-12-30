package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
