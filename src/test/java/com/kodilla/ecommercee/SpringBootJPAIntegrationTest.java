package com.kodilla.ecommercee;

import com.kodilla.ecommercee.repository.GenericEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class SpringBootJPAIntegrationTest {

    @Autowired
    private GenericEntityRepository genericEntityRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        GenericEntity genericEntity = genericEntityRepository
                .save(new GenericEntity("test"));
        Optional<GenericEntity> foundEntity = genericEntityRepository
                .findById(genericEntity.getId());
        System.out.println(foundEntity.get().getId());

        assertTrue(foundEntity.isPresent());
        assertEquals(genericEntity.getValue(), foundEntity.get().getValue());
    }
}
