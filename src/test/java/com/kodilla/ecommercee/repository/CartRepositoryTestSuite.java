package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRepositoryTestSuite {

    @Autowired
    CartRepository cartRepository;

    @Test
    public void testSaveCart() {
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
}
