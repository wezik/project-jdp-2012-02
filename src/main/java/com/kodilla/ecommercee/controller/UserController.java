package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService userService;
    private final UserMapper userMapper;

    @GetMapping("/getUser/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userMapper.mapToUserDto(userService.getUserById(id).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping("/getUsers")
    public List<UserDto> getUsers() {
        List<User> users = userService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User saveUSer = userService.saveUser(user);
        return userMapper.mapToUserDto(saveUSer);
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
    }
}
