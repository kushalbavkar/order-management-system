package com.kb.users.controller.v1;

import com.kb.common.dto.users.UsersDto;
import com.kb.common.dto.users.UsersResponseDto;
import com.kb.users.controller.UsersController;
import com.kb.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersControllerImpl implements UsersController {
    private final UsersService usersService;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersResponseDto>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUsers());
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersResponseDto> getUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserById(id));
    }

    @Override
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersResponseDto> saveUser(@RequestBody UsersDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.saveUser(user));
    }

    @Override
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersResponseDto> updateUser(@RequestBody UsersDto user, @PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.updateUserById(user, id));
    }

    @Override
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        usersService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
