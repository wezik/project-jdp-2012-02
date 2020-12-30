package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @NotNull
    @Column(name = "STATUS")
    private String status;
    @Column(name = "USERKEY")
    private Long userKey;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
