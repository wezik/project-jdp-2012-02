package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1/group")
public class GroupController {

    @GetMapping(value = "getGroups")
    public List<GroupDto> getGroups() {
        GroupDto[] groups = new GroupDto[]{
                new GroupDto(1L,"Test Group 1"),
                new GroupDto(2L,"Test Group 2"),
                new GroupDto(3L,"Test Group 3")
        };
        return Arrays.asList(groups);
    }

    @GetMapping(value = "getGroup/{id}")
    public GroupDto getGroup(@PathVariable Long id) {
        return new GroupDto(5L,"getGroup Test");
    }

    @PostMapping(value = "addGroup")
    public GroupDto addGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }
}
