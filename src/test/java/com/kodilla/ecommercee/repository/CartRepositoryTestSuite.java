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
    public void testRelationsBeforeAndAfterDeleteCart() {
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
        Long cartId = cart.getId();
        Long groupId = group.getId();
        Long productId = product.getId();
        Long cartEntryId = cartEntry.getId();

        Optional<Cart> cartExtractedBeforeCartDelete = cartRepository.findById(cartId);
        Optional<CartEntry> cartEntryExtractedBeforeCartDelete = cartEntryRepository.findById(cartEntryId);

        cartEntryExtractedBeforeCartDelete.get().removeRelationsFromCartAndProductTables();
        productRepository.save(cartEntryExtractedBeforeCartDelete.get().getProduct());
        cartRepository.deleteById(cartId);

        Optional<Cart> cartExtractedAfterCartDelete = cartRepository.findById(cartId);
        Optional<CartEntry> cartEntryExtractedAfterCartDelete = cartEntryRepository.findById(cartEntryId);
        Optional<Product> productExtractedAfterCartDelete = productRepository.findById(productId);
        Optional<Group> groupExtractedAfterCartDelete = groupRepository.findById(groupId);

        //Then
        assertEquals(cartEntryId, cartExtractedBeforeCartDelete.get().getCartEntryList().get(0).getId());
        assertEquals(cartId, cartEntryExtractedBeforeCartDelete.get().getCart().getId());
        assertFalse(cartEntryExtractedAfterCartDelete.isPresent());
        assertFalse(cartExtractedAfterCartDelete.isPresent());
        assertTrue(productExtractedAfterCartDelete.isPresent());
        assertTrue(groupExtractedAfterCartDelete.isPresent());

        //CleanUp
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }
}
