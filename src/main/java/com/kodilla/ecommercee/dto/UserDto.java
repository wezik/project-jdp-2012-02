package com.kodilla.ecommercee.dto;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class UserDto {

    private Long id;
    private String username;
    private String status;
    private Long userKey;
}
