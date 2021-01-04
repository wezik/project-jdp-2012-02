package com.kodilla.ecommercee.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {


    @Test
    public void testManualDependencyAssignments(){
        // given
        User user = new User(1L, "username", "active", 123456789L);
        Order order = new Order(1L, 49.99, "pending", LocalDateTime.now(), "address here",user, new Cart() );
    }

}