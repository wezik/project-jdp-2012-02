package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.CartProduct;
import com.kodilla.ecommercee.dto.CartProductDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartProductMapper {

    public CartProduct mapToCartProduct(final CartProductDto cartProductDto) {
        return new CartProduct(
                cartProductDto.getCart(),
                cartProductDto.getProduct(),
                cartProductDto.getQuantity()
        );
    }

    public CartProductDto mapToCartProductDto(final CartProduct cartProduct) {
        return new CartProductDto(
                cartProduct.getId(),
                cartProduct.getCart(),
                cartProduct.getProduct(),
                cartProduct.getQuantity()
        );
    }

    public List<CartProductDto> mapToCartProductDtoList(final List<CartProduct> products) {
        return products.stream()
                .map(this::mapToCartProductDto)
                .collect(Collectors.toList());
    }

}
