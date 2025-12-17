package com.kb.users.controller;

import com.kb.common.dto.users.UsersDto;
import com.kb.common.dto.users.UsersResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UsersController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UsersResponseDto>> getUsers();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsersResponseDto> getUserById(@PathVariable("id") Integer id);

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsersResponseDto> saveUser(@RequestBody UsersDto user);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsersResponseDto> updateUser(@RequestBody UsersDto user, @PathVariable("id") Integer id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id);
}
