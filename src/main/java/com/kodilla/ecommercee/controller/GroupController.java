package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupDbService groupDbService;
    private final GroupMapper groupMapper;

    @GetMapping(value = "getGroups")
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupDbService.getAllGroups());
    }

    @GetMapping(value = "getGroup/{id}")
    public GroupDto getGroup(@PathVariable Long id) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(groupDbService.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(value = "addGroup")
    public GroupDto addGroup(@RequestBody GroupDto groupDto) {
        Group savedGroup = groupDbService.saveGroup(groupMapper.mapToGroup(groupDto));
        return groupMapper.mapToGroupDto(savedGroup);
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        Group savedGroup = groupDbService.saveGroup(groupMapper.mapToGroup(groupDto));
        return groupMapper.mapToGroupDto(savedGroup);
    }
}
