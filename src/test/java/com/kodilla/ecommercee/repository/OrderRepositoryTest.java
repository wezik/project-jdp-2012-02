package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class OrderRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    @After
    public void clean() {
        userRepository.deleteAll();
        orderRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void testOrderRepositorySaveAndFindAll() {
        //Given
        Order order = new Order(12.0, "status_test", LocalDateTime.now(), "Address");
        orderRepository.save(order);
        //When
        List<Order> orders = orderRepository.findAll();
        Long idResult = order.getId();
        //Then
        assertEquals(1, orders.size());
        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testOrderFindById() {
        //Given
        Order order = new Order(12.0, "status_test", LocalDateTime.now(), "Address");
        orderRepository.save(order);
        Long id = order.getId();
        //When
        Optional<Order> resultFindById = orderRepository.findById(id);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testOrderSaveDbWithUserAndCard() {
        //Given
        User user = new User("username", "1", 1234L);
        Order order = new Order(12.0, "status_test", LocalDateTime.now(), "Address");
        orderRepository.save(order);
        Cart cart = new Cart();

        order.setUser(user);
        order.setCart(cart);
        //When
        userRepository.save(user);
        cartRepository.save(cart);
        Long id = order.getId();
        //Then
        assertEquals(order.getUser().getId(), user.getId());
        assertEquals(order.getCart().getId(), cart.getId());
    }
}
