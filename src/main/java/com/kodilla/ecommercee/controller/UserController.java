package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @GetMapping("/getUser/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return new UserDto(id, "test", "1", 1234L);
    }

    @GetMapping("/getUsers")
    public List<UserDto> getUsers() {
        return Arrays.asList(
                new UserDto(1L, "test1", "1", 1234L),
                new UserDto(2L, "test2", "1", 2234L),
                new UserDto(3L, "test", "0", 3234L)
        );
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userDto;
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userDto;
    }
}
