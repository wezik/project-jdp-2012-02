package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;
}
