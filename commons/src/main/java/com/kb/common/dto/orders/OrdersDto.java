package com.kb.common.dto.orders;

import com.kb.common.constants.orders.OrderStatus;

public record OrdersDto(
        Integer userId,
        Integer productId,
        Integer quantity,
        OrderStatus status
){}
