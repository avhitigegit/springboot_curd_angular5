package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = {"/"})
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userDtoList;
        ResponseEntity<List<UserDto>> response;
        userDtoList = userService.findAll();
        response = ResponseEntity.ok(userDtoList);
        return response;
    }

    @GetMapping(path = {"/user/{id}"})
    public ResponseEntity<UserDto> findOne(@PathVariable("id") Long id) {
        UserDto userDto;
        ResponseEntity<UserDto> response;
        userDto = userService.findOne(id);
        response = ResponseEntity.ok(userDto);
        return response;
    }

    @PostMapping(value = "/user/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        ResponseEntity<UserDto> response;
        userDto = userService.createUser(userDto);
        response = ResponseEntity.ok(userDto);
        return response;
    }

    @PutMapping(path = {"/user/update/{id}"})
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        ResponseEntity<UserDto> response;
        userDto = userService.updateUser(userDto);
        response = ResponseEntity.ok(userDto);
        return response;
    }

    @DeleteMapping(path = {"/user/delete/{id}"})
    public void delete(@RequestBody UserDto userDto) {
        userService.delete(userDto);
    }

}
