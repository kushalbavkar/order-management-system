package com.kb.users.repository.mapper;


import com.kb.common.dto.users.UsersDto;
import com.kb.common.dto.users.UsersResponseDto;
import com.kb.users.entity.Users;

public class EntityMapper {
    private EntityMapper() {
    }

    public static UsersResponseDto entityToDto(final Users entity) {
        return new UsersResponseDto(
                entity.getId(),
                entity.getUserName(),
                entity.getEmail()
        );
    }

    public static Users dtoToEntity(final UsersDto dto) {
        final Users entity = new Users();
        entity.setUserName(dto.userName());
        entity.setEmail(dto.email());
        return entity;
    }

    public static Users updateEntity(final Users entity, final UsersDto dto) {
        entity.setUserName(dto.userName());
        entity.setEmail(dto.email());
        return entity;
    }
}
