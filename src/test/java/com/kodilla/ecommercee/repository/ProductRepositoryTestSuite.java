package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.CartEntryDbService;
import com.kodilla.ecommercee.service.GroupDbService;
import com.kodilla.ecommercee.service.ProductDbService;
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
public class ProductRepositoryTestSuite {

    @Autowired
    private ProductDbService productDbService;
    @Autowired
    private GroupDbService groupDbService;
    @Autowired
    private CartDbService cartDbService;
    @Autowired
    private CartEntryDbService cartEntryDbService;

    @Test
    public void testCreateNewProductWithAssignedGroup() {
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
        long groupId = group.getId();
        String groupName = product.getGroup().getGroupName();
        String productName = group.getProductList().get(0).getName();

        //Then
        assertNotEquals(0, productId);
        assertEquals("RTV", groupName);
        assertEquals("TV", productName);

        //CleanUp
        try {
            productDbService.deleteProduct(productId);
            groupDbService.deleteGroup(groupId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testDeleteProductWhichBelongsToCartEntry() {
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

        cart.getCartEntryList().add(cartEntry);
        cartDbService.updateCart(cart);

        product.getCartEntriesWhichContainsThisEntry().add(cartEntry);
        productDbService.saveProduct(product);

        //When
        long productId = product.getId();
        long groupId = group.getId();
        long cartId = cart.getId();
        productDbService.deleteProduct(productId);
        Optional<Group> groupExtracted = groupDbService.getGroup(group.getId());
        Optional<Product> productExtracted = productDbService.getProduct(productId);
        Optional<CartEntry> entryExtracted = cartEntryDbService.getEntry(cartEntry.getId());
        Optional<Cart> cartExtracted = cartDbService.getCart(cart.getId());

        //Then
        assertTrue(cartExtracted.isPresent());
        assertTrue(groupExtracted.isPresent());
        assertEquals(0, cartExtracted.get().getCartEntryList().size());
        assertFalse(productExtracted.isPresent());
        assertFalse(entryExtracted.isPresent());

        //CleanUp
        try {
            cartDbService.deleteCart(cartId);
            groupDbService.deleteGroup(groupId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testChangeProductsGroupAndCartEntryList() {
        //Given
        Group group = groupDbService.saveGroup(new Group("RTV"));
        Optional<Group> groupExtracted = groupDbService.getGroup(group.getId());
        Group group1 = groupDbService.saveGroup(new Group("Small RTV"));
        Optional<Group> group1Extracted = groupDbService.getGroup(group1.getId());

        Product product = productDbService.saveProduct(new Product(
                "TV",
                "65 inches",
                new BigDecimal(3000),
                group));
        Optional<Product> productExtracted = productDbService.getProduct(product.getId());
        groupExtracted.get().getProductList().add(productExtracted.get());
        groupDbService.saveGroup(groupExtracted.get());

        Cart cart = cartDbService.createCart();
        Optional<Cart> cartExtracted = cartDbService.getCart(cart.getId());
        Cart cart1 = cartDbService.createCart();
        Optional<Cart> cart1Extracted = cartDbService.getCart(cart1.getId());

        CartEntry cartEntry = cartEntryDbService.saveEntry(new CartEntry(
                cart,
                product,
                2L
        ));
        Optional<CartEntry> entryExtracted = cartEntryDbService.getEntry(cartEntry.getId());
        CartEntry cartEntry1 = cartEntryDbService.saveEntry(new CartEntry(
                cart1,
                product,
                1L
        ));
        Optional<CartEntry> entry1Extracted = cartEntryDbService.getEntry(cartEntry1.getId());

        cartExtracted.get().getCartEntryList().add(entryExtracted.get());
        cartDbService.updateCart(cartExtracted.get());

        productExtracted.get().getCartEntriesWhichContainsThisEntry().add(entryExtracted.get());
        productDbService.saveProduct(productExtracted.get());

        //When
        Long groupId = groupExtracted.get().getId();

        productExtracted.get().setGroup(group1Extracted.get());
        productExtracted.get().getCartEntriesWhichContainsThisEntry().add(entry1Extracted.get());
        productDbService.saveProduct(product);

        //Then
        assertNotEquals(groupId, productExtracted.get().getGroup().getId());
        assertEquals(2, productExtracted.get().getCartEntriesWhichContainsThisEntry().size());
    }

}
