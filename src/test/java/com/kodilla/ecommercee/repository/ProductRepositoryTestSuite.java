package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        Optional<Product> extracted= productRepository.findById(productId);

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
    public void testDbRelationsBeforeAndAfterDeleteProduct() {
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
        long productId = product.getId();
        long cartId = cart.getId();
        long cartEntryId = cartEntry.getId();

        Optional<Product> productExtractedBeforeProductDelete = productRepository.findById(productId);
        long cartEntryAssignedToProductId = productExtractedBeforeProductDelete.get().getCartEntriesWhichContainsThisEntry().get(0).getId();

        Optional<Cart> cartExtractedBeforeProductDelete = cartRepository.findById(cart.getId());
        long cartEntryAssignedToCartId = cartExtractedBeforeProductDelete.get().getCartEntryList().get(0).getId();

        Optional<CartEntry> entryExtractedBeforeProductDelete = cartEntryRepository.findById(cartEntry.getId());
        long cartAssignedToCartEntryId = entryExtractedBeforeProductDelete.get().getCart().getId();
        long productAssignedToCartEntryId = entryExtractedBeforeProductDelete.get().getProduct().getId();

        List<CartEntry> entries = new ArrayList<>(productExtractedBeforeProductDelete.get().getCartEntriesWhichContainsThisEntry());
        for (CartEntry iterationEntry : entries) {
            iterationEntry.removeRelationsFromCartAndProductTables();
            cartEntryRepository.save(iterationEntry);
            cartEntryRepository.deleteById(iterationEntry.getId());
        }

        productRepository.deleteById(productId);

        Optional<Group> groupExtractedAfterProductDelete = groupRepository.findById(group.getId());
        Optional<Product> productExtractedAfterProductDelete = productRepository.findById(productId);
        Optional<CartEntry> entryExtractedAfterProductDelete = cartEntryRepository.findById(cartEntry.getId());
        Optional<Cart> cartExtractedAfterProductDelete = cartRepository.findById(cart.getId());

        //Then
        assertEquals(cartEntryAssignedToProductId, cartEntryId);
        assertEquals(cartEntryAssignedToCartId, cartEntryId);
        assertEquals(cartAssignedToCartEntryId, cartId);
        assertEquals(productAssignedToCartEntryId, productId);
        assertTrue(cartExtractedAfterProductDelete.isPresent());
        assertTrue(groupExtractedAfterProductDelete.isPresent());
        assertEquals(0, cartExtractedAfterProductDelete.get().getCartEntryList().size());
        assertFalse(productExtractedAfterProductDelete.isPresent());
        assertFalse(entryExtractedAfterProductDelete.isPresent());

        //CleanUp
        try {
            cartRepository.deleteById(cartId);
            groupRepository.delete(groupRepository.findById(group.getId()).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
