package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartEntryRepositoryTestSuite {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartEntryRepository cartEntryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void testSaveAndGetCartEntry() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        Cart cart = cartRepository.save(new Cart());

        CartEntry cartEntry = cartEntryRepository.save(new CartEntry(
                cart,
                product,
                2L
        ));

        cartEntry.setRelationsInCartAndProductJoinTables();
        cartRepository.save(cart);
        productRepository.save(product);

        //When
        Optional<CartEntry> cartEntryExtracted = cartEntryRepository.findById(cartEntry.getId());

        //Then
        assertTrue(cartEntryExtracted.isPresent());

        //CleanUp
        cartEntryExtracted.get().removeRelationsFromCartAndProductTables();
        cartEntryRepository.save(cartEntryExtracted.get());
        cartEntryRepository.deleteAll();
        productRepository.deleteAll();
        cartRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void testDeleteCartEntry() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        Cart cart = cartRepository.save(new Cart());

        CartEntry cartEntry = cartEntryRepository.save(new CartEntry(
                cart,
                product,
                2L
        ));

        cartEntry.setRelationsInCartAndProductJoinTables();
        cartRepository.save(cart);
        productRepository.save(product);

        //When
        Optional<CartEntry> cartEntryExtracted = cartEntryRepository.findById(cartEntry.getId());
        cartEntryExtracted.get().removeRelationsFromCartAndProductTables();
        cartEntryRepository.save(cartEntryExtracted.get());
        cartEntryRepository.deleteById(cartEntry.getId());

        //Then
        assertFalse(cartEntryRepository.findById(cartEntry.getId()).isPresent());

        //CleanUp
        productRepository.deleteAll();
        cartRepository.deleteAll();
        groupRepository.deleteAll();

    }

    @Test
    public void testDbRelationsBeforeDeleteCartEntry() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        Cart cart = cartRepository.save(new Cart());

        CartEntry cartEntry = cartEntryRepository.save(new CartEntry(
                cart,
                product,
                2L
        ));

        cartEntry.setRelationsInCartAndProductJoinTables();
        cartRepository.save(cart);
        productRepository.save(product);

        //When
        Optional<CartEntry> cartEntryExtracted = cartEntryRepository.findById(cartEntry.getId());
        CartEntry entryFromCart = cartRepository.findById(cart.getId()).get().getCartEntryList().get(0);
        CartEntry entryFromProduct = productRepository.findById(product.getId()).get().getCartEntriesWhichContainsThisEntry().get(0);

        //Then
        assertTrue(cartEntryExtracted.isPresent());
        assertEquals(entryFromCart.getId(), cartEntryExtracted.get().getId());
        assertEquals(entryFromProduct.getId(), cartEntryExtracted.get().getId());

        //CleanUp
        cartEntryExtracted.get().removeRelationsFromCartAndProductTables();
        cartEntryRepository.save(cartEntryExtracted.get());
        cartEntryRepository.deleteAll();
        productRepository.deleteAll();
        cartRepository.deleteAll();
        groupRepository.deleteAll();

    }

    @Test
    public void testDbRelationsAfterDeleteProduct() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        Cart cart = cartRepository.save(new Cart());

        CartEntry cartEntry = cartEntryRepository.save(new CartEntry(
                cart,
                product,
                2L
        ));

        cartEntry.setRelationsInCartAndProductJoinTables();
        cartRepository.save(cart);
        productRepository.save(product);

        //When
        Optional<CartEntry> cartEntryExtracted = cartEntryRepository.findById(cartEntry.getId());
        cartEntryExtracted.get().removeRelationsFromCartAndProductTables();
        cartEntryRepository.save(cartEntryExtracted.get());
        cartEntryRepository.deleteAll();
        Optional<Cart> cartExtracted = cartRepository.findById(cart.getId());
        Optional<Product> productExtracted = productRepository.findById(product.getId());

        //Then
        assertFalse(cartEntryRepository.findById(cartEntry.getId()).isPresent());
        assertTrue(cartExtracted.isPresent());
        assertTrue(productExtracted.isPresent());
        assertEquals(0, cartExtracted.get().getCartEntryList().size());
        assertEquals(0, productExtracted.get().getCartEntriesWhichContainsThisEntry().size());

        //CleanUp
        productRepository.deleteAll();
        cartRepository.deleteAll();
        groupRepository.deleteAll();

    }

}

