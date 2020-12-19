package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceeApplicationTests {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testyEncji() {

//        List<Cart> list = new ArrayList<>();
//
//        Cart cart1 = new Cart();
//        cartRepository.save(cart1);
//
//        Group group = new Group("Group1");
//        groupRepository.save(group);
//        Group createdGroup = groupRepository.findAll().get(0);
//
//        Product product1 = new Product(1L, "name", "description", new BigDecimal(1), createdGroup, list);
//        Product product2 = new Product(2L, "name1", "description1", new BigDecimal(5), createdGroup, list);
//        Product product3 = new Product(3L, "name2", "description2", new BigDecimal(10), createdGroup, list);
//
//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);

//        product1.getCartsWhichContainsThisProduct().add(cart1);
//        product2.getCartsWhichContainsThisProduct().add(cart1);
//        product3.getCartsWhichContainsThisProduct().add(cart1);

        Cart cart = cartRepository.findById(162L).get();
        List<Product> products = productRepository.findAll();
        cart.getProductList().addAll(products);
        cartRepository.save(cart);

//        cart1.getProductList().add(product1);
//        cart1.getProductList().add(product2);
//        cart1.getProductList().add(product3);

//        productRepository.save(product1);
//        productRepository.save(product2);
//        productRepository.save(product3);
//        cartRepository.save(cart1);

    }

}

