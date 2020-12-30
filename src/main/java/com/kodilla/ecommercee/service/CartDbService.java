package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.AddCartEntryDto;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartDbService {

    final CartRepository repository;
    final CartEntryDbService cartEntryDbService;
    private ProductDbService productDbService;

    @Autowired
    public void setProductDbService(ProductDbService productDbService) {
        this.productDbService = productDbService;
    }

    public Optional<Cart> getCart(Long cartId) {
        return repository.findById(cartId);
    }

    public Cart createCart() {
        return repository.save(new Cart());
    }

    public void updateCart(Cart cart) {
        repository.save(cart);
    }

    public void deleteCart(Long cartId) {
        repository.deleteById(cartId);
    }

    public List<CartEntry> getProducts(Long cartId) {
        return repository.findById(cartId).get().getCartEntryList();
    }

    public CartEntry addProduct(AddCartEntryDto addCartEntryDto) {
        Cart cartFromDb = getCart(addCartEntryDto.getCartId()).get();
        Product productFromDb = productDbService.getProduct(addCartEntryDto.getProductId()).get();
        CartEntry newEntry = cartEntryDbService.saveEntry(new CartEntry(
                cartFromDb,
                productFromDb,
                addCartEntryDto.getQuantity()
        ));

        cartFromDb.getCartEntryList().add(newEntry);
        productFromDb.getCartEntriesWhichContainsThisEntry().add(newEntry);

        updateCart(cartFromDb);
        productDbService.saveProduct(productFromDb);

        return newEntry;
    }

    public void deleteProduct(Long cartEntryId) {
        CartEntry entryFromDb = cartEntryDbService.getEntry(cartEntryId).get();
        Cart cartFromDb = entryFromDb.getCart();
        Product productFromDb = entryFromDb.getProduct();

        cartFromDb.getCartEntryList().remove(entryFromDb);
        productFromDb.getCartEntriesWhichContainsThisEntry().remove(entryFromDb);

        updateCart(cartFromDb);
        productDbService.saveProduct(productFromDb);
        cartEntryDbService.deleteEntry(entryFromDb.getId());
    }

}
