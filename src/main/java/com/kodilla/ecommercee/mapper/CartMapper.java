package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductListDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartMapper {

    final ProductMapper productMapper;

    public Cart mapToCart() {
        return new Cart();
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                productMapper.mapProductListToProductDtoList(cart.getProductList())
        );
    }

    public List<ProductListDto> mapToProductListDto(List<Product> products) {
        return productMapper.mapProductListToProductDtoList(products);
    }

}
