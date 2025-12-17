package com.kb.users.service;

import com.kb.common.dto.users.UsersDto;
import com.kb.common.dto.users.UsersResponseDto;
import com.kb.users.exceptions.UsersException;
import com.kb.users.repository.UsersRepository;
import com.kb.users.repository.mapper.EntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersResponseDto> getAllUsers() {
        log.info("Getting all users");
        return usersRepository
                .findAll()
                .stream()
                .map(EntityMapper::entityToDto)
                .toList();
    }

    public UsersResponseDto getUserById(final int id) {
        log.info("Getting user by id: {}", id);
        return usersRepository
                .findById(id)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new UsersException("Missing user with id: " + id));
    }

    public UsersResponseDto saveUser(final UsersDto user) {
        log.info("Saving user: {}", user);
        return Optional
                .of(EntityMapper.dtoToEntity(user))
                .map(usersRepository::save)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new UsersException("Unable to save user: " + user));
    }

    public UsersResponseDto updateUserById(final UsersDto user, final int id) {
        log.info("Updating user with id: {}", id);
        return usersRepository
                .findById(id)
                .map(entity -> EntityMapper.updateEntity(entity, user))
                .map(usersRepository::save)
                .map(EntityMapper::entityToDto)
                .orElseThrow(() -> new UsersException("Failed to update user with id: " + id));
    }

    public void deleteUserById(final int id) {
        log.info("Deleting user with id: {}", id);
        usersRepository
                .deleteById(id);
    }
}
