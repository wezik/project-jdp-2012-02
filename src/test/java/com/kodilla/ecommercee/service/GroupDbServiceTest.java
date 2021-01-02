package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class GroupDbServiceTest {

    @Autowired
    GroupDbService groupDbService;

    @Test
    public void group_CRUD() {
        //Create

            //Given
            Group sample = new Group("Test");

            //When
            Group result = groupDbService.saveGroup(sample);

            //Then
            assertEquals(sample.getGroupName(),result.getGroupName());

        //Read

            //Given

            //When
            Optional<Group> read = groupDbService.getGroup(result.getId());

            //Then
            assertTrue(read.isPresent());
            assertEquals(read.get().getGroupName(),result.getGroupName());

        //Update

            //Given
            Group updateSample = new Group(result.getId(),"Updated name",result.getProductList());

            //When
            Group update = groupDbService.saveGroup(updateSample);

            //Then
            assertNotEquals(update,result);
            assertEquals(update.getGroupName(),"Updated name");
    }
}
