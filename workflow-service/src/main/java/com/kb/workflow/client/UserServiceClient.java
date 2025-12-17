package com.kb.workflow.client;

import com.kb.common.dto.users.UsersDto;
import com.kb.common.dto.users.UsersResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "user-service",
        url = "${services.user-service.url}"
)
public interface UserServiceClient {
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UsersResponseDto>> getUsers();

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsersResponseDto> getUserById(@PathVariable("id") Integer id);

    @PostMapping(value = "/users/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsersResponseDto> saveUser(@RequestBody UsersDto user);

    @PutMapping(value = "/users/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsersResponseDto> updateUser(@RequestBody UsersDto user, @PathVariable("id") Integer id);

    @DeleteMapping(value = "/users/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id);
}
