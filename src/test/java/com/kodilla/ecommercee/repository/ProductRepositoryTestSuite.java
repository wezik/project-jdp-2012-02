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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRepositoryTestSuite {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartEntryRepository cartEntryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void testSaveNewProduct() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        //When
        long productId = product.getId();
        String groupName = product.getGroup().getGroupName();
        String productName = group.getProductList().get(0).getName();

        //Then
        assertNotEquals(0, productId);
        assertEquals("RTV", groupName);
        assertEquals("TV", productName);

        //CleanUp
        try {
            productRepository.deleteById(productId);
            groupRepository.delete(groupRepository.findById(group.getId()).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGetTargetProduct() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        //WHen
        long productId = product.getId();
        Optional<Product> extracted = productRepository.findById(productId);

        //Then
        assertTrue(extracted.isPresent());

        //CleanUp
        try {
            productRepository.deleteById(productId);
            groupRepository.delete(groupRepository.findById(group.getId()).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGetAllProducts() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        Product product1 = productRepository.save(new Product(
                "Computer",
                "apple",
                new BigDecimal(4000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        //When
        List<Product> extractedProducts = productRepository.findAll();

        //Then
        assertEquals(2, extractedProducts.size());

        //CleanUp
        try {
            productRepository.deleteById(product.getId());
            productRepository.deleteById(product1.getId());
            groupRepository.delete(groupRepository.findById(group.getId()).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testUpdateProduct() {
        //Given
        Group group = groupRepository.save(new Group("RTV"));
        Product product = productRepository.save(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupRepository.save(group);

        //When
        Product updatedProduct = productRepository.save(new Product(
                        product.getId(),
                        "TV updated",
                        "Desc updated",
                        new BigDecimal(3500),
                        groupRepository.findById(group.getId()).get(),
                        product.getCartEntriesWhichContainsThisEntry()
                )
        );

        //Then
        assertEquals(updatedProduct.getName(), "TV updated");
        assertEquals(updatedProduct.getDescription(), "Desc updated");
        assertEquals(updatedProduct.getPrice(), new BigDecimal(3500));

        //CleanUp
        try {
            productRepository.deleteById(product.getId());
            groupRepository.delete(groupRepository.findById(group.getId()).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testDbRelationsBeforeDeleteProduct() {
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
        Optional<Group> extractedGroup = groupRepository.findById(group.getId());
        Long productAssignedToGroup = extractedGroup.get().getProductList().get(0).getId();
        Optional<CartEntry> extractedEntry = cartEntryRepository.findById(cartEntry.getId());
        Long productAssignedToCartEntry = extractedEntry.get().getProduct().getId();
        Optional<Cart> extractedCart = cartRepository.findById(cart.getId());

        //Then
        assertEquals(productAssignedToGroup, product.getId());
        assertEquals(productAssignedToCartEntry, product.getId());

        //CleanUp
        extractedCart.get().getCartEntryList().clear();
        cartRepository.save(extractedCart.get());
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

        CartEntry entry = cartEntryRepository.findById(cartEntry.getId()).get();
        entry.removeRelationsFromCartAndProductTables();
        cartRepository.save(entry.getCart());
        productRepository.deleteById(product.getId());

        //Then
        assertTrue(cartRepository.findById(cart.getId()).isPresent());
        assertTrue(groupRepository.findById(group.getId()).isPresent());
        assertEquals(0, cartRepository.findById(cart.getId()).get().getCartEntryList().size());
        assertFalse(productRepository.findById(product.getId()).isPresent());

        //CleanUp
        cartRepository.deleteAll();
        groupRepository.deleteAll();

    }
}
