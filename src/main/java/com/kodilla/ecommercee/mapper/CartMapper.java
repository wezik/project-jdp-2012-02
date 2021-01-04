package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartMapper {

    final CartEntryMapper cartEntryMapper;

    public Cart mapToCart(CartDto cartDto) {
        return new Cart(
                cartDto.getId()
        );
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                cartEntryMapper.mapToCartEntryDtoList(cart.getProductList())
        );
    }

}
