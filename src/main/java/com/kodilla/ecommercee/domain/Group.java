package com.kodilla.ecommercee.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "GROUPS")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID",unique = true)
    Long id;

    @Column(name = "GROUP_NAME")
    String groupName;
}
