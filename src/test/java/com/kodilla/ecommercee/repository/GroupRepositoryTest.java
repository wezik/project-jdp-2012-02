package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
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
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void givenGroupRepository_whenSaveAndRetrieveGroup_thenOK() {
        //Given
        Group group = groupRepository.save(new Group(1L,"test"));

        //When
        Optional<Group> foundGroup = groupRepository.findById(group.getId());

        //Then
        assertTrue(foundGroup.isPresent());
        assertEquals(group.getGroupName(), foundGroup.get().getGroupName());
    }
}
