package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @After
    public void clean() {
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void testUserRepositorySave() {
        //Given
        User user = new User("username", "1", 1234L);
        //When
        userRepository.save(user);
        //Then
        Long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        assertTrue(readUser.isPresent());
        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserFindAll() {
        //Given
        User user1 = new User("first_user", "1", 1234L);
        User user2 = new User("second_user", "0", 1232L);
        userRepository.save(user1);
        userRepository.save(user2);
        //When
        List<User> users = userRepository.findAll();
        //Then
        assertEquals(2, users.size());
        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserFindById() {
        //Given
        User user = new User("username", "1", 1234L);
        userRepository.save(user);
        Long id = user.getId();
        //When
        Optional<User> resultFindById = userRepository.findById(id);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserSaveDbWithOrder() {
        //Given
        User user = new User("username", "1", 1234L);
        Order order = new Order();
        order.setUser(user);
        //When
        userRepository.save(user);
        Long userLong = user.getId();
        //Then
        assertEquals(order.getUser().getId(), user.getId());
        //Clean-up
        userRepository.deleteAll();
    }
}
