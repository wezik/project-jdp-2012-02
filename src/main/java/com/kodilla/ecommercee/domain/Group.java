package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "GROUPS_LIST")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "GROUP_ID", unique = true)
    Long id;

    @Column(name = "GROUP_NAME")
    @NotNull
    @NonNull
    String groupName;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            fetch = FetchType.EAGER
    )
    List<Product> productList = new ArrayList<>();
}
