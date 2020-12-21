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

        Cart cart1 = new Cart();
        cartRepository.save(cart1);
        Cart cart1Extracted = cartRepository.findById(cart1.getId()).get();
        System.out.println(cart1Extracted);
        Cart cart2 = new Cart();
        cartRepository.save(cart2);
        Cart cart2Extracted = cartRepository.findById(cart2.getId()).get();

        Group owoce = new Group("Owoce");
        groupRepository.save(owoce);
        Group owoceExtracted = groupRepository.findById(owoce.getId()).get();
        Group rtv = new Group("RTV");
        groupRepository.save(rtv);
        Group rtvExtracted = groupRepository.findById(rtv.getId()).get();

        Product apple = new Product("Jabłko", "bio jabłko", new BigDecimal(10), owoceExtracted);
        Product orange = new Product("Pomarańcza", "bio pomarńcza", new BigDecimal(50), owoceExtracted);
        Product tv = new Product("Telewizor", "65 cali", new BigDecimal(4000), rtvExtracted);
        Product computer = new Product("Komputer", "komputer przenośny", new BigDecimal(5000), rtvExtracted);

        productRepository.save(apple);
        Long appleId = apple.getId();
        System.out.println(appleId);
        productRepository.save(orange);
        Long orangeId = orange.getId();
        productRepository.save(tv);
        Long tvId = tv.getId();
        productRepository.save(computer);
        Long computerId = computer.getId();

        cart1Extracted.getProductList().add(productRepository.findById(appleId).get());
        cart1Extracted.getProductList().add(productRepository.findById(orangeId).get());
        cart2Extracted.getProductList().add(productRepository.findById(tvId).get());
        cart2Extracted.getProductList().add(productRepository.findById(computerId).get());

        cartRepository.save(cart1Extracted);
        cartRepository.save(cart2Extracted);

    }

}

