package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.AddCartEntryDto;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartDbService {

    final CartRepository repository;
    final CartEntryDbService cartEntryDbService;
    final ProductDbService productDbService;

    public Cart getCart(Long cartId) {
        return repository.findById(cartId).get();
    }

    public Cart createCart() {
        return repository.save(new Cart());
    }

    public void updateCart(Cart cart) {
        repository.save(cart);
    }

    public List<CartEntry> getProducts(Long cartId) {
        return repository.findById(cartId).get().getCartEntryList();
    }

    public CartEntry addProduct(AddCartEntryDto addCartEntryDto) {
        Cart cartFromDb = getCart(addCartEntryDto.getCartId());
        Product productFromDb = productDbService.getProduct(addCartEntryDto.getProductId());

        CartEntry newEntry = cartEntryDbService.saveEntry(new CartEntry(
                cartFromDb,
                productFromDb,
                addCartEntryDto.getQuantity()
        ));

        newEntry.setRelationsInCartAndProductJoinTables();

        updateCart(cartFromDb);
        productDbService.saveProduct(productFromDb);

        return newEntry;
    }

    public void deleteProduct(Long cartEntryId) {
        CartEntry entryFromDb = cartEntryDbService.getEntry(cartEntryId);
        Cart cartFromDb = entryFromDb.getCart();
        Product productFromDb = entryFromDb.getProduct();

        entryFromDb.removeRelationsFromCartAndProductTables();

        updateCart(cartFromDb);
        productDbService.saveProduct(productFromDb);
        cartEntryDbService.deleteEntry(entryFromDb.getId());
    }

}
