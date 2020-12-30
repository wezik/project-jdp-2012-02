package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartEntryDto;
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
public class CartEntryMapper {

    final ProductMapper productMapper;
    final CartDbService cartDbService;

    public CartEntry mapToCartEntry(final CartEntryDto cartEntryDto) {
        Cart cart = cartDbService.getCart(cartEntryDto.getCartId()).get();
        Product product = productMapper.mapToProduct(cartEntryDto.getProductDetails());
        return new CartEntry(
                cart,
                product,
                cartEntryDto.getQuantity()
        );
    }

    public CartEntryDto mapToCartEntryDto(final CartEntry cartEntry) {
        ProductDto productDto = productMapper.mapToProductDto(cartEntry.getProduct());
        return new CartEntryDto(
                cartEntry.getId(),
                cartEntry.getCart().getId(),
                productDto,
                cartEntry.getQuantity()
        );
    }

    public List<CartEntryDto> mapToCartEntryDtoList(final List<CartEntry> products) {
        return products.stream()
                .map(this::mapToCartEntryDto)
                .collect(Collectors.toList());
    }

}
