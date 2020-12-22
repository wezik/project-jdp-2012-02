package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartProduct;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartProductDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.CartDbService;
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

    final ProductMapper productMapper;
    final CartDbService cartDbService;

    public CartProduct mapToCartProduct(final CartProductDto cartProductDto) {
        Cart cart = cartDbService.getCart(cartProductDto.getCartId());
        Product product = productMapper.mapToProduct(cartProductDto.getProductDetails());
        return new CartProduct(
                cart,
                product,
                cartProductDto.getQuantity()
        );
    }

    public CartProductDto mapToCartProductDto(final CartProduct cartProduct) {
        ProductDto productDto = productMapper.mapToProductDto(cartProduct.getProduct());
        return new CartProductDto(
                cartProduct.getId(),
                cartProduct.getCart().getId(),
                productDto,
                cartProduct.getQuantity()
        );
    }

    public List<CartProductDto> mapToCartProductDtoList(final List<CartProduct> products) {
        return products.stream()
                .map(this::mapToCartProductDto)
                .collect(Collectors.toList());
    }

}
