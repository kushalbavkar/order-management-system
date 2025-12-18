package com.kb.common.dto.orders;

import com.kb.common.constants.orders.OrderStatus;
import com.kb.common.dto.MandatoryFieldsValidator;
import com.kb.common.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public record OrdersDto(
        Integer userId,
        Integer productId,
        Integer quantity,
        OrderStatus status
) implements MandatoryFieldsValidator {

    @Override
    public void validate() {
        final List<String> errors = new ArrayList<>();
        if (userId == null) errors.add("userId must not be null");
        if (productId == null) errors.add("productId must not be null");
        if (quantity == null) errors.add("quantity must not be null");
        if (status == null) errors.add("status must not be null");

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}
