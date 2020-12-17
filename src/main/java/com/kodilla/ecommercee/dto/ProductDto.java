package com.kodilla.ecommercee.dto;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    @NonNull
    private String name;
}
