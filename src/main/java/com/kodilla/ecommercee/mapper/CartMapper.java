package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    @Autowired
    ProductMapper productMapper;

    public Cart mapToCart() {
        return new Cart();
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                productMapper.mapToProductDtoList(cart.getProductList())
        );
    }

}
