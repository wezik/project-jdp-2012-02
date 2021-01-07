package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.CartDbService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRepositoryTestSuite {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartEntryRepository cartEntryRepository;

    @Test
    public void testSaveAndGetCart() {
        //Given
        Cart cart = cartRepository.save(new Cart());

        //When
        Optional<Cart> extracted = cartRepository.findById(cart.getId());

        //Then
        assertTrue(extracted.isPresent());

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    public void testDeleteCart() {
        //Given
        Cart cart = cartRepository.save(new Cart());

        //When
        cartRepository.deleteById(cart.getId());
        Optional<Cart> extracted = cartRepository.findById(cart.getId());

        //Then
        assertFalse(extracted.isPresent());

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    public void testRelationsBeforeDeleteCart() {
        //Given
        Cart cart = cartRepository.save(new Cart());

        Group group = groupRepository.save(new Group("RTV"));

        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));

        group.getProductList().add(product);
        groupRepository.save(group);

        CartEntry cartEntry = cartEntryRepository.save(new CartEntry(
                cart,
                product,
                2L
        ));
        cartEntry.setRelationsInCartAndProductJoinTables();
        cartRepository.save(cart);
        productRepository.save(product);

        //When
        Optional<Cart> extractedCart = cartRepository.findById(cart.getId());
        Long cartEntryAssignedToCartId = extractedCart.get().getCartEntryList().get(0).getId();
        Optional<Product> extractedProduct = productRepository.findById(product.getId());
        Optional<CartEntry> extractedCartEntry = cartEntryRepository.findById(cartEntry.getId());

        //Then
        assertEquals(cartEntryAssignedToCartId, cartEntry.getId());

        //CleanUp
        extractedCartEntry.get().removeRelationsFromCartAndProductTables();
        cartEntryRepository.save(extractedCartEntry.get());
        cartEntryRepository.deleteAll();
        productRepository.deleteAll();
        cartRepository.deleteAll();
        groupRepository.deleteAll();

    }

    @Test
    public void testRelationsAfterDeleteCart() {
        //Given
        Cart cart = cartRepository.save(new Cart());

        Group group = groupRepository.save(new Group("RTV"));

        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));

        group.getProductList().add(product);
        groupRepository.save(group);

        CartEntry cartEntry = cartEntryRepository.save(new CartEntry(
                cart,
                product,
                2L
        ));
        cartEntry.setRelationsInCartAndProductJoinTables();
        cartRepository.save(cart);
        productRepository.save(product);

        //When
        Optional<CartEntry> cartEntryExtractedBeforeCartDelete = cartEntryRepository.findById(cartEntry.getId());
        cartEntryExtractedBeforeCartDelete.get().removeRelationsFromCartAndProductTables();
        productRepository.save(cartEntryExtractedBeforeCartDelete.get().getProduct());
        cartRepository.deleteById(cart.getId());

        //Then
        assertFalse(cartEntryRepository.findById(cartEntry.getId()).isPresent());
        assertFalse(cartRepository.findById(cart.getId()).isPresent());
        assertTrue(productRepository.findById(product.getId()).isPresent());
        assertTrue(groupRepository.findById(group.getId()).isPresent());

        //CleanUp
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }
}
