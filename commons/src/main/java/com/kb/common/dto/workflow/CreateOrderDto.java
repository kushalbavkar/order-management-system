package com.kb.common.dto.workflow;

import com.kb.common.dto.MandatoryFieldsValidator;
import com.kb.common.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public record CreateOrderDto(
        String userName,
        String productName,
        Integer quantity
) implements MandatoryFieldsValidator {

    @Override
    public void validate() {
        final List<String> errors = new ArrayList<>();

        if (userName == null || userName.isBlank()) errors.add("userName must not be null or blank");
        if (productName == null || productName.isBlank()) errors.add("email must not be null or blank");
        if (quantity == null) errors.add("quantity must not be null");

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}
