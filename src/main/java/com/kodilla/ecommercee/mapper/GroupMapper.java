package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {
    public Group mapToGroup(GroupDto groupDto) {
        return new Group(groupDto.getGroupName());
    }

    public GroupDto mapToGroupDto(Group group) {
        return new GroupDto(group.getId(),group.getGroupName());
    }

    public List<GroupDto> mapToGroupDtoList(List<Group> groups) {
        return groups.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
