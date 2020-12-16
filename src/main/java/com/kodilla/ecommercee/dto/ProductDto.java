package com.kodilla.ecommercee.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    @NonNull
    private String name;

    public ProductDto(@NonNull String name) {
        this.name = name;
    }
}
