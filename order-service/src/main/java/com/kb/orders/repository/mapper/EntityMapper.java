package com.kb.orders.repository.mapper;

import com.kb.common.dto.orders.OrdersDto;
import com.kb.common.dto.orders.OrdersResponseDto;
import com.kb.orders.entity.Orders;

public class EntityMapper {
    private EntityMapper() {
    }

    public static OrdersResponseDto entityToDto(final Orders entity) {
        return new OrdersResponseDto(
                entity.getId(),
                entity.getUserId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getStatus()
        );
    }

    public static Orders dtoToEntity(final OrdersDto dto) {
        final Orders entity = new Orders();
        entity.setUserId(dto.userId());
        entity.setProductId(dto.productId());
        entity.setQuantity(dto.quantity());
        entity.setStatus(dto.status());
        return entity;
    }

    public static Orders updateEntity(final Orders entity, final OrdersDto dto) {
        entity.setUserId(dto.userId());
        entity.setProductId(dto.productId());
        entity.setQuantity(dto.quantity());
        entity.setStatus(dto.status());
        return entity;
    }
}
