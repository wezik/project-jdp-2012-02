package com.kodilla.ecommercee.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "GROUPS_LIST")
public class Group {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID", unique = true)
    Long id;

    @Column(name = "GROUP_NAME")
    @NotNull
    @NonNull
    String groupName;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupId",
            fetch = FetchType.EAGER
    )
    List<Product> productList;
}
