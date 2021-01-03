package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
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

    @Autowired
    GroupRepository groupRepository;

    @Test
    public void group_crud_create_test() {
        //Given

        //When
        Group result1 = groupDbService.saveGroup(new Group("Create"));
        Group result2 = groupDbService.saveGroup(new Group("Test"));

        //Then
        assertTrue(groupRepository.findById(result1.getId()).isPresent());
        assertTrue(groupRepository.findById(result2.getId()).isPresent());

        //Cleanup
        groupRepository.deleteAll();
    }

    @Test
    public void group_crud_read_test() {
        //Given
        Group sample = groupRepository.save(new Group("Read"));

        //When
        Optional<Group> result = groupDbService.getGroup(sample.getId());

        //Then
        assertTrue(result.isPresent());
        assertEquals(result.get().getGroupName(),"Read");

        //Cleanup
        groupRepository.deleteAll();
    }

    @Test
    public void group_crud_update_test() {
        //Given
        Group sample = groupRepository.save(new Group("Update"));
        int size = groupRepository.findAll().size();

        //When
        Group result = groupDbService.saveGroup(new Group(sample.getId(),"Updated Name",sample.getProductList()));

        //Then
        assertEquals(sample.getId(),result.getId());
        assertEquals(result.getGroupName(),"Updated Name");
        assertEquals(groupRepository.findAll().size(),size);

        //Cleanup
        groupRepository.deleteAll();
    }

    @Test
    public void group_crud_delete_test() {
        //Given
        Group sample = groupRepository.save(new Group("Delete"));
        int size = groupRepository.findAll().size();

        //When
        groupDbService.deleteGroup(sample);

        //Then
        assertEquals(groupRepository.findAll().size(),size-1);

        //Cleanup
        groupRepository.deleteAll();
    }
}
