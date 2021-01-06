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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRepositoryTestSuite {

    @Autowired
    ProductDbService productDbService;
    @Autowired
    GroupDbService groupDbService;
    @Autowired
    CartDbService cartDbService;
    @Autowired
    CartEntryDbService cartEntryDbService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartEntryRepository cartEntryRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testSaveNewProduct() {
        //Given
        Group group = groupDbService.saveGroup(new Group("RTV"));
        Product product = productDbService.saveProduct(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupDbService.saveGroup(group);

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
            productDbService.deleteProduct(productId);
            groupDbService.deleteGroup(group);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGetTargetProduct() {
        //Given
        Group group = groupDbService.saveGroup(new Group("RTV"));
        Product product = productDbService.saveProduct(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupDbService.saveGroup(group);

        //WHen
        long productId = product.getId();
        Optional<Product> extracted= Optional.ofNullable(productDbService.getProduct(productId));

        //Then
        assertTrue(extracted.isPresent());

        //CleanUp
        try {
            productDbService.deleteProduct(productId);
            groupDbService.deleteGroup(group);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGetAllProducts() {
        //Given
        Group group = groupDbService.saveGroup(new Group("RTV"));
        Product product = productDbService.saveProduct(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        Product product1 = productDbService.saveProduct(new Product(
                "Computer",
                "apple",
                new BigDecimal(4000),
                group));
        group.getProductList().add(product);
        groupDbService.saveGroup(group);

        //When
        List<Product> extractedProducts = productDbService.getProducts();

        //Then
        assertEquals(2, extractedProducts.size());

        //CleanUp
        try {
            productDbService.deleteProduct(product.getId());
            productDbService.deleteProduct(product1.getId());
            groupDbService.deleteGroup(group);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testUpdateProduct() {
        //Given
        Group group = groupDbService.saveGroup(new Group("RTV"));
        Product product = productDbService.saveProduct(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupDbService.saveGroup(group);

        //When
        Product updated = productDbService.updateProduct(new ProductDto(
                product.getId(),
                "TV updated",
                "Desc updated",
                new BigDecimal(3500),
                group.getId()
                )
        );

        //Then
        assertEquals(updated.getName(), "TV updated");
        assertEquals(updated.getDescription(), "Desc updated");
        assertEquals(updated.getPrice(), new BigDecimal(3500));

        //CleanUp
        try {
            productDbService.deleteProduct(product.getId());
            groupDbService.deleteGroup(group);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testDbRelationsAfterDeleteProduct() {
        //Given
        Group group = groupDbService.saveGroup(new Group("RTV"));

        Product product = productDbService.saveProduct(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        group.getProductList().add(product);
        groupDbService.saveGroup(group);

        Cart cart = cartDbService.createCart();

        CartEntry cartEntry = cartEntryDbService.saveEntry(new CartEntry(
                cart,
                product,
                2L
        ));

        cartEntry.setRelationsInCartAndProductJoinTables();
        cartDbService.updateCart(cart);
        productDbService.saveProduct(product);

        //When
        long productId = product.getId();
        long cartId = cart.getId();
        productDbService.deleteProduct(productId);
        Optional<Group> groupExtracted = groupDbService.getGroup(group.getId());
        Optional<Product> productExtracted = productRepository.findById(productId);
        Optional<CartEntry> entryExtracted = cartEntryRepository.findById(cartEntry.getId());
        Optional<Cart> cartExtracted = cartRepository.findById(cart.getId());

        //Then
        assertTrue(cartExtracted.isPresent());
        assertTrue(groupExtracted.isPresent());
        assertEquals(0, cartExtracted.get().getCartEntryList().size());
        assertFalse(productExtracted.isPresent());
        assertFalse(entryExtracted.isPresent());

        //CleanUp
        try {
            cartDbService.deleteCart(cartId);
            groupDbService.deleteGroup(group);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
