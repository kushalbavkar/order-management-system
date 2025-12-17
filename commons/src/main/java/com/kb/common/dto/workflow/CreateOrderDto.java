package com.kb.common.dto.workflow;

public record CreateOrderDto(
        String userName,
        String productName,
        Integer quantity
){}
