package com.kodilla.ecommercee.dto;

import lombok.Value;

@Value
public class UserDto {

    private Long id;
    private String username;
    private String status;
    private Long userKey;
}
